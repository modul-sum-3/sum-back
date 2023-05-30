package com.fitness.fitnessBack.carnet_transaction.repository;

import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import com.fitness.fitnessBack.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<CarnetTransaction, Long> {
    List<CarnetTransaction> findCarnetTransactionByExpireDateAfter(ZonedDateTime now);
    List<CarnetTransaction> findCarnetTransactionByClientID(Client clientID);
}
