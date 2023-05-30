package com.fitness.fitnessBack.carnets.controller;

import com.fitness.fitnessBack.carnets.model.Carnet;
import com.fitness.fitnessBack.carnets.service.CarnetService;
import com.fitness.fitnessBack.user.model.User;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/carnets", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarnetController {
    CarnetService carnetService;
    @GetMapping()
    public List<Carnet> getAll() {
        return carnetService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Carnet> getOne(@PathVariable(value = "id") String id) {
        return carnetService.getOne(id);
    }
    @PostMapping("/create")
    public Carnet createCarnet(@Valid @RequestBody Carnet carnet, @AuthenticationPrincipal User user) {
        return carnetService.createCarnet(carnet, user);
    }
}
