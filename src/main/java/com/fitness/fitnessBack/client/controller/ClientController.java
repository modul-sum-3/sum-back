package com.fitness.fitnessBack.client.controller;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.service.ClientService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Value
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    @GetMapping
    public List<Client> findAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client findOne(@PathVariable(value = "id") UUID id) {
        return clientService.getOne(id);
    }

    ClientService clientService;
    @GetMapping("/email/{email}")
    public Client getClientByEmail(@PathVariable String email){
        return clientService.getClientByEmail(email);
    }


    @PostMapping
    public Client savePatient(@Valid @RequestBody Client client){
        return clientService.saveClient(client);
    }

    @DeleteMapping("/{id}")
    public Client deleteClient(@PathVariable(value = "id") UUID id) {
        return clientService.deleteClient(id);
    }
}
