package com.fitness.fitnessBack.training.repository;

import com.fitness.fitnessBack.category.model.Category;
import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.training.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    List<Training> findAllByCategory(Category category);

    List<Training> findAllByIsConfirmed(boolean isConfirmed);

    List<Training> findAllByClient(Client client);

    List<Training> findAllByTrainer(Trainer trainer);

}
