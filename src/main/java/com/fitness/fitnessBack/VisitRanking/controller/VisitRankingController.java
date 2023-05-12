package com.fitness.fitnessBack.VisitRanking.controller;

import com.fitness.fitnessBack.VisitRanking.model.VisitRanking;
import com.fitness.fitnessBack.VisitRanking.service.VisitRankingService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Value
@CrossOrigin
@RequestMapping(value="/Ranking", produces = MediaType.APPLICATION_JSON_VALUE)
public class VisitRankingController {
    VisitRankingService visitRankingService;

    @GetMapping
    public List<VisitRanking> findAll() {
        return visitRankingService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<VisitRanking> findOne(@PathVariable(value = "id") Long id) {
        return visitRankingService.getOne(id);
    }
    @PostMapping
    public VisitRanking saveTrainer(@Valid @RequestBody VisitRanking ranking) {
        return visitRankingService.saveVisitRanking(ranking);
    }

    @DeleteMapping("/{id}")
    public VisitRanking deleteTrainer(@PathVariable(value = "id") Long id) {
        return visitRankingService.deleteVisitRanking(id);
    }
}
