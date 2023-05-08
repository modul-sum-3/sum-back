package com.fitness.fitnessBack.trainer.service;

import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.trainer.repository.TrainerRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Value
public class TrainerService {
    TrainerRepository trainerRepository;

    public List<Trainer> getAll() {
        List<Trainer> result = new ArrayList<>();
        result.addAll(trainerRepository.findAll());

        return result;
    }
    public Trainer getOne(UUID id) {
        return trainerRepository.findById(id).orElseThrow();
    }


    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public Trainer deleteTrainer(UUID id){
        Trainer result = trainerRepository.findById(id).orElseThrow();
        trainerRepository.delete(result);
        return result;
    }
}
