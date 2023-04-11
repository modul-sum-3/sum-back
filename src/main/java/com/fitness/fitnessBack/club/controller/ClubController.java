package com.fitness.fitnessBack.club.controller;

import com.fitness.fitnessBack.club.model.Club;
import com.fitness.fitnessBack.club.service.ClubService;

import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Value
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/clubs", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClubController {
    ClubService clubService;

    @GetMapping
    public List<Club> findAll() {
        return clubService.getAll();
    }
    @GetMapping("/{id}")
    public Club findOne(@PathVariable(value = "id") Long id) {
        return clubService.getOne(id);
    }
    @PostMapping
    public Club saveClub(@Valid @RequestBody Club club) {
        return clubService.saveClub(club);
    }

    @DeleteMapping("/{id}")
    public Club deleteClub(@PathVariable(value = "id") Long id) {
        return clubService.deleteClub(id);
    }
}
