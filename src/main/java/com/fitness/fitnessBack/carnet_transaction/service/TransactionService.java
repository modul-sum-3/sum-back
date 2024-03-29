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
    public List<CarnetTransaction> findAllByClient(UUID id, User user) {
        List<CarnetTransaction> list = transactionRepository.findCarnetTransactionByClientID(clientRepository.findById(id).orElseThrow());
        if(id.equals(user.getId())) {
            return list;
        }
        if(user.getRole().equals(Role.EMPLOYEE) || user.getRole().equals(Role.MANAGER)) {
            return list;
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't see Transaction!");
    }

    public CarnetTransaction findActiveByClient(UUID id, User user) {
        CarnetTransaction list = transactionRepository.findCarnetTransactionByClientIDAndExpireDateAfter(clientRepository.findById(id).orElseThrow(),ZonedDateTime.now());
        if(list == null){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "User don't have active carnet!");
        }
        if (id.equals(user.getId())) {
            return list;
        }
        if (user.getRole().equals(Role.EMPLOYEE) || user.getRole().equals(Role.MANAGER)) {
            return list;
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can't see Transaction!");


    }

    public ResponseEntity<?> saveTransaction(CarnetTransaction carnetTransaction, User user) {
        Client client = clientRepository.findById(carnetTransaction.getClientID().getId()).orElseThrow();
        if(!user.getRole().equals(Role.EMPLOYEE) && !user.getRole().equals(Role.MANAGER) && !user.getId().equals(client.getId())) {
            return ResponseEntity.badRequest().body("You can't bought this carnet!");
        }
        Carnet choose = carnetRepository.findById(carnetTransaction.getCarnetID()).get();
        if(!(transactionRepository.findCarnetTransactionByClientIDAndExpireDateAfter(client,ZonedDateTime.now()) == null)) {
            return ResponseEntity.badRequest().body("User have active carnet!");
        }
        carnetTransaction.setPrice(choose.getPrice());
        if(client.getBalance() < carnetTransaction.getPrice()) {
            return ResponseEntity.badRequest().body("You can't afford this carnet!");
        }
        client.setBalance(client.getBalance() - carnetTransaction.getPrice());
        carnetTransaction.setExpireDate(carnetTransaction.getTransactionDate().plus(choose.getDuration(),ChronoUnit.DAYS));
        carnetTransaction.setClientID(client);
        transactionRepository.save(carnetTransaction);
        return ResponseEntity.ok().body(carnetTransaction);
    }

}
