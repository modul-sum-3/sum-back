package com.fitness.fitnessBack.trainer.service;

import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.trainer.repository.TrainerRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class TrainerService {
    TrainerRepository trainerRepository;

    public List<Trainer> getAll() {
        List<Trainer> result = new ArrayList<>();
        result.addAll(trainerRepository.findAll());

        return result;
    }
    public Trainer getOne(Long id) {
        return trainerRepository.findById(id).orElseThrow();
    }

    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
}
