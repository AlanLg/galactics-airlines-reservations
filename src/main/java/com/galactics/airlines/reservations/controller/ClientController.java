package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalaticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
import com.galactics.airlines.reservations.model.dto.response.ClientDTOResponse;
import com.galactics.airlines.reservations.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDTOResponse> addClient(@RequestBody ClientDTORequest clientDTORequest) throws GalaticsAirlinesException {
        return ResponseEntity.ok(clientService.addClient(clientDTORequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDTOResponse> updateClient(@PathVariable Long id, @RequestBody ClientDTORequest clientDTORequest) throws GalaticsAirlinesException {
        return ResponseEntity.ok(clientService.updateClient(id, clientDTORequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        try {
            clientService.deleteClient(id);
            return ResponseEntity.accepted().build();
        } catch (GalaticsAirlinesException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
