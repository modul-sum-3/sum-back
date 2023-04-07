package com.fitness.fitnessBack.trainer.controller;

import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.trainer.service.TrainerService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Value
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/trainer", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrainerController {
    TrainerService trainerService;

    @GetMapping
    public List<Trainer> findAll() {
        return trainerService.getAll();
    }
    @GetMapping("/{id}")
    public Trainer findOne(@PathVariable(value = "id") Long id) {
        return trainerService.getOne(id);
    }
    @PostMapping
    public Trainer saveTrainer(@Valid @RequestBody Trainer trainer) {
        return trainerService.saveTrainer(trainer);
    }
}
