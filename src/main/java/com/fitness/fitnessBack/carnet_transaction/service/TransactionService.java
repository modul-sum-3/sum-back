package com.fitness.fitnessBack.carnet_transaction.service;

import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import com.fitness.fitnessBack.carnet_transaction.repository.TransactionRepository;
import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.repository.ClientRepository;
import com.fitness.fitnessBack.user.model.User;
import lombok.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Value
public class TransactionService {
    ClientRepository clientRepository;
    TransactionRepository transactionRepository;
    public List<CarnetTransaction> getAll() {
        List<CarnetTransaction> result = new ArrayList<>();
        result.addAll(transactionRepository.findAll());

        return result;
    }

    public Optional<CarnetTransaction> getOne(Long id) {
        return transactionRepository.findById(id);
    }

    public ResponseEntity<?> saveTransaction(CarnetTransaction carnetTransaction, User user) {
        Client client = clientRepository.findById(user.getId()).get();
        if(client.getBalance() < carnetTransaction.getPrice()) {
            return ResponseEntity.badRequest().body("You can't afford this carnet!");
        } else {
            client.setBalance(client.getBalance() - carnetTransaction.getPrice());
            carnetTransaction.setClientID(client);
            transactionRepository.save(carnetTransaction);
           return ResponseEntity.ok().body(carnetTransaction);
        }
    }

}
