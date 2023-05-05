package com.fitness.fitnessBack.training.service;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.repository.ClientRepository;
import com.fitness.fitnessBack.training.model.Training;
import com.fitness.fitnessBack.training.repository.TrainingRepository;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class TrainingService {

    TrainingRepository trainingRepository;
    ClientRepository clientRepository;

    public List<Training> getAll() {
        List<Training> result = new ArrayList<>();
        result.addAll(trainingRepository.findAll());

        return result;
    }
    public Training getOne(Long id) {
        return trainingRepository.findById(id).orElseThrow();
    }

    public Training saveTraining(Training training) {
        return trainingRepository.save(training);
    }

    public Training deleteTraining(Long id){
        Training result = trainingRepository.findById(id).orElseThrow();
        trainingRepository.delete(result);
        return result;
    }
    public Training addClient(Long TrainingID, Client client){
        Training training = trainingRepository.findById(TrainingID).orElseThrow();
        Client toAdd = clientRepository.getClientByEmailIgnoreCase(client.getEmail());
        if(training.getClients().size() < training.getAmount()) {
            if (toAdd.equals(null)) {
                clientRepository.save(toAdd);
                training.getClients().add(toAdd);
            } else {
                training.getClients().add(toAdd);
            }
            trainingRepository.save(training);
            return training;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Training limit is full!");

    }
}