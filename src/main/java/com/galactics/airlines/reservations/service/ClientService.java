package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
import com.galactics.airlines.reservations.model.dto.response.ClientDTOResponse;
import com.galactics.airlines.reservations.model.entity.Client;
import com.galactics.airlines.reservations.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    ClientDTOResponse addClient(ClientDTORequest clientDTORequest);
    ClientDTOResponse updateClient(Long id, ClientDTORequest clientDTORequest);
    void deleteClient(Long id);
}
