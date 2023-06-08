package com.fitness.fitnessBack.client.controller;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.service.ClientService;
import jakarta.websocket.server.PathParam;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    ClientService clientService;

    @GetMapping
    public List<Client> findAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Client> findOne(@PathVariable(value = "id") UUID id) {
        return clientService.getOne(id);
    }
    @PatchMapping ("/{id}")
    public ResponseEntity<?> addBalance(@PathVariable(value = "id") UUID id, @PathParam(value = "amount") Long amount) {
        return clientService.addBalace(id, amount);
    }

    @PatchMapping ("/change/{id}")
    public Client changeClient(@PathVariable(value = "id") UUID id ,@RequestBody Client client) {
        return clientService.changeClient(id,client);
    }

    @DeleteMapping("/{id}")
    public Client deleteClient(@PathVariable(value = "id") UUID id) {
        return clientService.deleteClient(id);
    }
}
