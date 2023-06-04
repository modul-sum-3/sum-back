package com.fitness.fitnessBack.VisitRanking.repository;

import com.fitness.fitnessBack.VisitRanking.model.VisitRanking;
import com.fitness.fitnessBack.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRankingRepository extends JpaRepository<VisitRanking, Long> {
    @Query("SELECT v FROM VisitRanking v where v.End_visit is NULL and v.client = ?1")
    VisitRanking findVisitRankingByClient(Client client);


}
