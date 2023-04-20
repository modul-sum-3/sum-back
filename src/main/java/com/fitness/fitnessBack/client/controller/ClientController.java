package com.fitness.fitnessBack.client.controller;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.service.ClientService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Value
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    ClientService clientService;

    @GetMapping
    public List<Client> findAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client findOne(@PathVariable(value = "id") Long id) {
        return clientService.getOne(id);
    }

    @PostMapping
    public Client saveClient(@Valid @RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @DeleteMapping("/{id}")
    public Client deleteClient(@PathVariable(value = "id") Long id) {
        return clientService.deleteClient(id);
    }
}
