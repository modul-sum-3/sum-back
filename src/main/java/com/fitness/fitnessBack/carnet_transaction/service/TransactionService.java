package com.fitness.fitnessBack.carnet_transaction.service;

import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import com.fitness.fitnessBack.carnet_transaction.repository.TransactionRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Value
public class TransactionService {
    TransactionRepository transactionRepository;
    public List<CarnetTransaction> getAll() {
        List<CarnetTransaction> result = new ArrayList<>();
        result.addAll(transactionRepository.findAll());

        return result;
    }

    public Optional<CarnetTransaction> getOne(Long id) {
        return transactionRepository.findById(id);
    }

    public CarnetTransaction saveTransaction(CarnetTransaction carnetTransaction) {
        return transactionRepository.save(carnetTransaction);
    }

}
