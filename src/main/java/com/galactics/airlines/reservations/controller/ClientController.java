package com.galactics.airlines.reservations.controller;

import com.galactics.airlines.reservations.exception.GalacticsAirlinesException;
import com.galactics.airlines.reservations.model.dto.request.ClientDTORequest;
import com.galactics.airlines.reservations.model.dto.response.ClientDTOResponse;
import com.galactics.airlines.reservations.model.dto.response.FlightDTOResponse;
import com.galactics.airlines.reservations.service.ClientService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/client")
@OpenAPIDefinition(info = @Info(title = "Galatics Airlines API", version = "v1"))
@SecurityRequirement(name = "basicAuth")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ClientDTOResponse> getClient(@PathVariable Long id) throws GalacticsAirlinesException {
        return ResponseEntity.ok(clientService.getClient(id));
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDTOResponse> addClient(@Valid @RequestBody ClientDTORequest clientDTORequest) throws GalacticsAirlinesException {
        log.info("Adding client: {}", clientDTORequest.toString());
        return ResponseEntity.ok(clientService.addClient(clientDTORequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDTOResponse> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTORequest clientDTORequest) throws GalacticsAirlinesException {
        log.info("Updating client: {}", clientDTORequest.toString());
        return ResponseEntity.ok(clientService.updateClient(id, clientDTORequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        log.info("Deleting client {}", id);
        try {
            clientService.deleteClient(id);
            return ResponseEntity.accepted().build();
        } catch (GalacticsAirlinesException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
