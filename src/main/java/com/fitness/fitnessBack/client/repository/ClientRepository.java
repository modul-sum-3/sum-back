package com.fitness.fitnessBack.client.repository;

import com.fitness.fitnessBack.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client getClientByEmailIgnoreCase(String email);
}
