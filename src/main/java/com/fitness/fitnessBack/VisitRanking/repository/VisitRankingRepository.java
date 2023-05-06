package com.fitness.fitnessBack.VisitRanking.repository;

import com.fitness.fitnessBack.VisitRanking.model.VisitRanking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRankingRepository extends JpaRepository<VisitRanking, Long> {

}
