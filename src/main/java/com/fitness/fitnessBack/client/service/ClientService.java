package com.fitness.fitnessBack.client.service;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.repository.ClientRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class ClientService {
    ClientRepository clientRepository;

    public List<Client> getAll() {
        List<Client> result = new ArrayList<>();
        result.addAll(clientRepository.findAll());

        return result;
    }

    public Client getOne(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client deleteClient(Long id) {
        Client result = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(result);
        return result;
    }
}
