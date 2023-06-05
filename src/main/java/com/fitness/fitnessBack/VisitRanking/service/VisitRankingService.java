package com.fitness.fitnessBack.VisitRanking.service;

import com.fitness.fitnessBack.VisitRanking.model.Rating;
import com.fitness.fitnessBack.VisitRanking.model.VisitRanking;
import com.fitness.fitnessBack.VisitRanking.repository.VisitRankingRepository;
import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.client.repository.ClientRepository;
import com.fitness.fitnessBack.training.model.Training;
import com.fitness.fitnessBack.training.repository.TrainingRepository;
import lombok.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Value
@Service
public class VisitRankingService {
    VisitRankingRepository visitRankingRepository;
    TrainingRepository trainingRepository;
    ClientRepository clientRepository;

    public List<VisitRanking> getAll() {
        List<VisitRanking> result = new ArrayList<>();
        result.addAll(visitRankingRepository.findAll());

        return result;
    }
    public Optional<VisitRanking> findByClientID(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow();
        return Optional.ofNullable(visitRankingRepository.findVisitRankingByClient(client));
    }

    public Optional<VisitRanking> getOne(Long id) {
        return visitRankingRepository.findById(id);
    }

    public VisitRanking startVisitRanking(VisitRanking ranking) {
        Training training = trainingRepository.findById(ranking.getTraining().getId()).orElseThrow();
        Client client = clientRepository.findById(ranking.getClient().getId()).orElseThrow();
        for(Client c : training.getClients()) {
            if (client.getId().equals(c.getId())) {
                ranking.setTraining(training);
                return visitRankingRepository.save(ranking);
            }
        }
        ranking.setTraining(null);
        return visitRankingRepository.save(ranking);
    }
    public VisitRanking endVisitRanking(Long id, Rating rating) {
        VisitRanking ranking = visitRankingRepository.findById(id).orElseThrow();
        ranking.setEnd_visit(ZonedDateTime.now());
        ranking.setRating(rating);
        return visitRankingRepository.save(ranking);
    }

    public VisitRanking endVisitRankingByClientID(UUID id, Rating rating) {
        Client client =  clientRepository.findById(id).orElseThrow();
        VisitRanking ranking = visitRankingRepository.findVisitRankingByClient(client);
        ranking.setEnd_visit(ZonedDateTime.now());
        ranking.setRating(rating);
        return visitRankingRepository.save(ranking);
    }


    public VisitRanking deleteVisitRanking(Long id){
        VisitRanking result = visitRankingRepository.findById(id).orElseThrow();
        visitRankingRepository.delete(result);
        return result;
    }

    @Scheduled(cron = "0 50 23 * * *")
    //Not tested hope it's works!!!
    public void closeAllVists() {
        for( VisitRanking v : visitRankingRepository.findAll()) {
            if(v.getEnd_visit() == null) {
                v.setEnd_visit(ZonedDateTime.now());
                v.setEnd_visit(ZonedDateTime.now());
                visitRankingRepository.save(v);
            }
        }
    }
}
