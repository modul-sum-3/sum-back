package com.fitness.fitnessBack.carnet_transaction.service;

import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import com.fitness.fitnessBack.carnet_transaction.repository.TransactionRepository;
import com.fitness.fitnessBack.carnets.model.Carnet;
import com.fitness.fitnessBack.carnets.repository.CarnetRepository;
import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.repository.ClientRepository;
import com.fitness.fitnessBack.user.model.Role;
import com.fitness.fitnessBack.user.model.User;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Value
public class TransactionService {
    ClientRepository clientRepository;
    CarnetRepository carnetRepository;
    TransactionRepository transactionRepository;
    public List<CarnetTransaction> getAll() {
        List<CarnetTransaction> result = new ArrayList<>();
        result.addAll(transactionRepository.findAll());

        return result;
    }

    public Optional<CarnetTransaction> getOne(Long id) {
        return transactionRepository.findById(id);
    }

    public List<CarnetTransaction> findBefore(){
        return transactionRepository.findCarnetTransactionByExpireDateAfter(ZonedDateTime.now());
    }
    public List<CarnetTransaction> findByClient(UUID id, User user) {
        if(user.getRole().equals(Role.EMPLOYEE) || user.getRole().equals(Role.MANAGER)) {
            return transactionRepository.findCarnetTransactionByClientID(clientRepository.findById(id).orElseThrow());
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't see Transaction!");
    }
    public ResponseEntity<?> saveTransaction(CarnetTransaction carnetTransaction, User user) {
        Client client = clientRepository.findById(user.getId()).get();
        Carnet choose = carnetRepository.findById(carnetTransaction.getCarnetID()).get();
        carnetTransaction.setPrice(choose.getPrice());
        if(client.getBalance() < carnetTransaction.getPrice()) {
            return ResponseEntity.badRequest().body("You can't afford this carnet!");
        }
        client.setBalance(client.getBalance() - carnetTransaction.getPrice());
        carnetTransaction.setClientID(client);
        carnetTransaction.setTransactionDate(carnetTransaction.getTransactionDate().plus(choose.getDuration(),ChronoUnit.DAYS));
        transactionRepository.save(carnetTransaction);
        return ResponseEntity.ok().body(carnetTransaction);
    }

}
