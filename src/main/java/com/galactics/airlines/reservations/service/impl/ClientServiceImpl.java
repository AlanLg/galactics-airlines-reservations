package com.galactics.airlines.reservations.service.impl;

import com.galactics.airlines.reservations.exception.ClientAlreadyExistsException;
import com.galactics.airlines.reservations.exception.ClientNotFoundException;
import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.mapper.ClientMapper;
import com.galactics.airlines.reservations.mapper.ReservationMapper;
import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
import com.galactics.airlines.reservations.model.dto.request.ReservationDTORequestWithExistingClient;
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
    public ClientDTOResponse getClient(Long id) {
        return ClientMapper.INSTANCE.clientEntityToClientDTOResponse(clientRepository.findById(id).orElseThrow(ClientNotFoundException::new));
    }

    @Override
    public ClientDTOResponse addClient(ClientDTORequest clientDTORequest) throws GalacticsAirlinesException {
        Client createdClient = ClientMapper.INSTANCE.clientDTORequestToClientEntity(clientDTORequest);

        if (!ClientValidationUtils.isValidClient(createdClient)) {
            throw new GalacticsAirlinesException("missing elements in the JSON");
        }

        Optional<Client> existingClient = clientRepository.findByEmail(createdClient.getEmail());

        if (existingClient.isPresent()) {
            log.info("Client already exists with email: {}", createdClient.getEmail());
            throw new ClientAlreadyExistsException();
        }

        log.info("created client: {}", createdClient);
        Client client = clientRepository.save(createdClient);
        return ClientMapper.INSTANCE.clientEntityToClientDTOResponse(client);
    }

    @Override
    public ClientDTOResponse updateClient(Long id, ClientDTORequest clientDTORequest) throws GalacticsAirlinesException {
        if (id == null || clientRepository.findById(id).isEmpty()) {
            throw new GalacticsAirlinesException("Aucun client en bdd");
        }

        Client updatedClient = ClientMapper.INSTANCE.clientDTORequestToClientEntity(clientDTORequest);

        if (!ClientValidationUtils.isValidClient(updatedClient)) {
            throw new GalacticsAirlinesException("missing elements in the JSON");
        }

        updatedClient.setId(id);
        clientRepository.save(updatedClient);
        return ClientMapper.INSTANCE.clientEntityToClientDTOResponse(updatedClient);
    }

    @Override
    public void deleteClient(Long id) throws GalacticsAirlinesException {
        if (id == null) {
            throw new GalacticsAirlinesException("missing elements in the JSON");
        }

        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            clientRepository.delete(client);
        } else {
            throw new GalacticsAirlinesException("Aucun client en base");
        }
    }

    @Override
    public Client createClientForReservation(ReservationDTORequestWithNoExistingClient reservationDTORequestWithNoExistingClient) {
        ClientDTORequest clientDTORequest = ReservationMapper.INSTANCE.reservationDTORequestToClientDTORequest(reservationDTORequestWithNoExistingClient);
        ClientDTOResponse clientDTOResponse = addClient(clientDTORequest);
        return ClientMapper.INSTANCE.clientDTOResponseToClientEntity(clientDTOResponse);
    }

    @Override
    public Client getClientForReservation(ReservationDTORequestWithExistingClient reservationDTORequestWithExistingClient) {
        return clientRepository.findByEmail(reservationDTORequestWithExistingClient.getEmail())
                .orElseThrow(() -> {
                        log.info("Client pas trouve");
                        return new ClientNotFoundException();
                    }
                );
    }
}
