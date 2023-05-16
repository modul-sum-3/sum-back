package com.fitness.fitnessBack.carnet_transaction.controller;

import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import com.fitness.fitnessBack.carnet_transaction.service.TransactionService;

import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/Transaction", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public CarnetTransaction saveTransaction(@Valid @RequestBody CarnetTransaction carnetTransaction) {
        return transactionService.saveTransaction(carnetTransaction);
    }
}
