package com.fitness.fitnessBack.carnet_transaction.repository;

import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<CarnetTransaction, Long> {
    List<CarnetTransaction> findCarnetTransactionByExpireDateBefore(ZonedDateTime now);
    List<CarnetTransaction> findCarnetTransactionByClientID(UUID id);
}
