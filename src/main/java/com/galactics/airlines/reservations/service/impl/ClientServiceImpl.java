package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.mapper.ClientMapper;
import com.galactics.airlines.reservations.mapper.ReservationMapper;
import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithNoExistingClient;
import com.galactics.airlines.reservations.model.dto.response.ClientDTOResponse;
import com.galactics.airlines.reservations.model.entity.Client;
import com.galactics.airlines.reservations.repository.ClientRepository;
import com.galactics.airlines.reservations.service.ClientService;
import com.galactics.airlines.reservations.utils.ClientValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTOResponse addClient(ClientDTORequest clientDTORequest) throws GalaticsAirlinesException {
        Client createdClient = ClientMapper.INSTANCE.clientDTORequestToClientEntity(clientDTORequest);

        if (!ClientValidationUtils.isValidClient(createdClient)) {
            throw new GalaticsAirlinesException("Il manque un élément dans le JSON");
        }

        Optional<Client> existingClient = clientRepository.findByEmail(createdClient.getEmail());

        if (existingClient.isPresent()) {
            return ClientMapper.INSTANCE.clientEntityToClientDTOResponse(existingClient.get());
        }

        return ClientMapper.INSTANCE.clientEntityToClientDTOResponse(clientRepository.save(createdClient));
    }

    @Override
    public ClientDTOResponse updateClient(Long id, ClientDTORequest clientDTORequest) throws GalaticsAirlinesException {
        if (id == null || clientRepository.findById(id).isEmpty()) {
            throw new GalaticsAirlinesException("Aucun client en bdd");
        }

        Client updatedClient = ClientMapper.INSTANCE.clientDTORequestToClientEntity(clientDTORequest);

        if (!ClientValidationUtils.isValidClient(updatedClient)) {
            throw new GalaticsAirlinesException("Il manque un élément dans le JSON");
        }

        updatedClient.setId(id);
        clientRepository.save(updatedClient);
        return ClientMapper.INSTANCE.clientEntityToClientDTOResponse(updatedClient);
    }

    @Override
    public void deleteClient(Long id) throws GalaticsAirlinesException {
        if (id == null) {
            throw new GalaticsAirlinesException("Il manque un élément dans le JSON");
        }

        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            clientRepository.delete(client);
        } else {
            throw new GalaticsAirlinesException("Aucun client en base");
        }
    }

    @Override
    public Client createClientForReservation(ReservationDTORequestWithNoExistingClient reservationDTORequestWithNoExistingClient) {
        ClientDTORequest clientDTORequest = ReservationMapper.INSTANCE.reservationDTORequestToClientDTORequest(reservationDTORequestWithNoExistingClient);
        ClientDTOResponse clientDTOResponse = addClient(clientDTORequest);
        return ClientMapper.INSTANCE.clientDTOResponseToClientEntity(clientDTOResponse);
    }
}
