package com.fitness.fitnessBack.training.repository;

import com.fitness.fitnessBack.training.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findAllByIsConfirmed(boolean isConfirmed);

}
