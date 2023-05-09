package com.fitness.fitnessBack.client.repository;

import com.fitness.fitnessBack.client.model.Client;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client getClientByEmailIgnoreCase(String email);
}
