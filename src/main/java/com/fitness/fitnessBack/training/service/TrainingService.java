package com.fitness.fitnessBack.training.service;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.repository.ClientRepository;
import com.fitness.fitnessBack.room.model.Room;
import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.training.model.Training;
import com.fitness.fitnessBack.training.repository.TrainingRepository;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.ZonedDateTime;
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
        ZonedDateTime startTime = training.getStartDate();
        ZonedDateTime endTime = startTime.plus(training.getDuration());
        Room room = training.getRoom();

        Trainer trainer = training.getTrainer();

        for (Training t : trainingRepository.findAll()) {
            if (t.getTrainer().equals(trainer)) {
                ZonedDateTime tStartTime = t.getStartDate();
                ZonedDateTime tEndTime = tStartTime.plus(t.getDuration());

                if (tStartTime.isBefore(endTime) && tEndTime.isAfter(startTime)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trainer is busy during this time!");
                }
            }

            if (t.getRoom().equals(room)) {
                ZonedDateTime tStartTime = t.getStartDate();
                ZonedDateTime tEndTime = tStartTime.plus(t.getDuration());

                if (tStartTime.isBefore(endTime) && tEndTime.isAfter(startTime)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is busy during this time!");
                }
            }
        }

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
