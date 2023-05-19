package com.fitness.fitnessBack.training.controller;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.training.model.Training;
import com.fitness.fitnessBack.training.service.TrainingService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/training", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrainingController {
    TrainingService trainingService;

    @GetMapping
    public List<Training> findAll() {
        return trainingService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Training> findOne(@PathVariable(value = "id") Long id) {
        return trainingService.getOne(id);
    }

    @GetMapping("/confirmed")
    public List<Training> findAllAcceptedTrainings() {
        return trainingService.findAllAcceptedTrainings();
    }

    @PutMapping("/{id}/confirmed")
    public Training confirmTraining(
            @PathVariable(value = "id") Long id,
            @RequestParam("isConfirmed") boolean isConfirmed) {

        return trainingService.confirmTraining(id, isConfirmed);
    }

    @PostMapping
    public Training saveTraining(@Valid @RequestBody Training training) {
        return trainingService.saveTraining(training);
    }

    @PostMapping("/client")
    public Training addClient(@RequestParam Long TrainingID, @Valid @RequestBody Client client) {
        return trainingService.addClient(TrainingID, client);
    }

    @DeleteMapping("/{id}")
    public Training deleteTraining(@PathVariable(value = "id") Long id) {
        return trainingService.deleteTraining(id);
    }
}
