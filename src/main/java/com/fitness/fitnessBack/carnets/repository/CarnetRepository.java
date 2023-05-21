package com.fitness.fitnessBack.carnets.repository;

import com.fitness.fitnessBack.carnets.model.Carnet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarnetRepository extends MongoRepository<Carnet, Long> {
}
