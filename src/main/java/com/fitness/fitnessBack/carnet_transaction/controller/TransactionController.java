package com.fitness.fitnessBack.carnet_transaction.controller;

import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import com.fitness.fitnessBack.carnet_transaction.service.TransactionService;

import com.fitness.fitnessBack.user.model.User;
import jakarta.validation.Valid;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    TransactionService transactionService;
    @GetMapping
    public List<CarnetTransaction> findAll() {
        return transactionService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<CarnetTransaction> findOne(@PathVariable(value = "id") Long id) {
        return transactionService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> saveTransaction(@Valid @RequestBody CarnetTransaction carnetTransaction, @AuthenticationPrincipal User user) {
        return transactionService.saveTransaction(carnetTransaction, user);
    }
}
