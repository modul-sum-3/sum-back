package com.fitness.fitnessBack.client.service;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.repository.ClientRepository;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Value
public class ClientService {

    ClientRepository clientRepository;

    public List<Client> getAll() {
        List<Client> result = new ArrayList<>();
        result.addAll(clientRepository.findAll());

        return result;
    }

    public Optional<Client> getOne(UUID id) {
        return clientRepository.findById(id);
    }

    public Client deleteClient(UUID id) {
        Client result = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(result);
        return result;
    }
    public ResponseEntity<?> addBalace(UUID id,Long amount) {
        if(amount <= 0L) {
            return ResponseEntity.badRequest().body("amount can't be <= 0!");
        }
        Client result = clientRepository.findById(id).orElseThrow();
        result.setBalance(result.getBalance() + amount);
        clientRepository.save(result);
        return ResponseEntity.ok("Added to Balance: " + amount.toString());
    }
}
