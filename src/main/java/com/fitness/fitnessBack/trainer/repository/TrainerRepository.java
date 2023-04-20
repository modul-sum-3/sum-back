package com.fitness.fitnessBack.trainer.repository;

import com.fitness.fitnessBack.trainer.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}
