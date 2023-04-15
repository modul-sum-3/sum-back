package com.fitness.fitnessBack.club.repository;

import com.fitness.fitnessBack.club.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {
}

