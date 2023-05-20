package com.fitness.fitnessBack.trainer.controller;

import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.trainer.model.TrainerPass;
import com.fitness.fitnessBack.trainer.service.TrainerService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/trainer", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrainerController {
    TrainerService trainerService;

    @GetMapping
    public List<Trainer> findAll() {
        return trainerService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Trainer> findOne(@PathVariable(value = "id") UUID id) {
        return trainerService.getOne(id);
    }

    @PostMapping
    public Trainer saveTrainer(@Valid @RequestBody TrainerPass trainer) {
        return trainerService.saveTrainer(trainer);
    }

    @DeleteMapping("/{id}")
    public Trainer deleteTrainer(@PathVariable(value = "id") UUID id) {
        return trainerService.deleteTrainer(id);
    }
}
