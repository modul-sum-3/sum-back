package com.fitness.fitnessBack.VisitRanking.controller;

import com.fitness.fitnessBack.VisitRanking.model.Rating;
import com.fitness.fitnessBack.VisitRanking.model.VisitRanking;
import com.fitness.fitnessBack.VisitRanking.service.VisitRankingService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("client/{id}")
    public Optional<VisitRanking> findByClient(@PathVariable(value = "id") UUID id) {
        return visitRankingService.findByClientID(id);
    }
    @PostMapping
    public VisitRanking startVisit(@Valid @RequestBody VisitRanking ranking) {
        return visitRankingService.startVisitRanking(ranking);
    }

    @PatchMapping("/{id}/{rating}")
    public VisitRanking endVisit(@PathVariable Long id,@PathVariable Rating rating) {
        return visitRankingService.endVisitRanking(id,rating);
    }
    @PatchMapping("client/{id}/{rating}")
    public VisitRanking endVisitClietn(@PathVariable UUID id,@PathVariable Rating rating) {
        return visitRankingService.endVisitRankingByClientID(id, rating);
    }
}
