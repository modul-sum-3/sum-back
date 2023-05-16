package com.fitness.fitnessBack.carnet_transaction.repository;

import com.fitness.fitnessBack.carnet_transaction.model.CarnetTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<CarnetTransaction, Long> {
}
