package com.fitness.fitnessBack.VisitRanking.service;

import com.fitness.fitnessBack.VisitRanking.model.VisitRanking;
import com.fitness.fitnessBack.VisitRanking.repository.VisitRankingRepository;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Value
@Service
public class VisitRankingService {
    VisitRankingRepository visitRankingRepository;

    public List<VisitRanking> getAll() {
        List<VisitRanking> result = new ArrayList<>();
        result.addAll(visitRankingRepository.findAll());

        return result;
    }
    public Optional<VisitRanking> getOne(Long id) {
        return visitRankingRepository.findById(id);
    }

    public VisitRanking saveVisitRanking(VisitRanking ranking) {
        if(ranking.getTraining().getClients().contains(ranking.getClient())) {
            return visitRankingRepository.save(ranking);
        } else throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Client doesn't take part in Training!");
    }

    public VisitRanking deleteVisitRanking(Long id){
        VisitRanking result = visitRankingRepository.findById(id).orElseThrow();
        visitRankingRepository.delete(result);
        return result;
    }
}
