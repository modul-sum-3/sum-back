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
import java.util.Optional;

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

    public List<Training> findAllAcceptedTrainings() {
        List<Training> result = new ArrayList<>();
        result.addAll(trainingRepository.findAllByIsConfirmed(true));

        return result;
    }

    public Optional<Training> getOne(Long id) {
        return trainingRepository.findById(id);
    }

    public Training saveTraining(Training training) {
        return trainingRepository.save(training);
    }

    public Training deleteTraining(Long id) {
        Training result = trainingRepository.findById(id).orElseThrow();
        trainingRepository.delete(result);
        return result;
    }

    public Training addClient(Long TrainingID, Client client) {
        Training training = trainingRepository.findById(TrainingID).orElseThrow();
        Client toAdd = clientRepository.getClientByEmailIgnoreCase(client.getEmail());
        if (training.getClients().size() < training.getAmount()) {
            if (!toAdd.equals(null)) {
                training.getClients().add(toAdd);
                trainingRepository.save(training);
                return training;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User no exists!");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Training limit is full!");

    }
}
