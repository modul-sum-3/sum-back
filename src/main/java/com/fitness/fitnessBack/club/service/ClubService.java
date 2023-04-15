package com.fitness.fitnessBack.club.service;


import com.fitness.fitnessBack.club.model.Club;
import com.fitness.fitnessBack.club.repository.ClubRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class ClubService {
    ClubRepository clubRepository;

    public List<Club> getAll() {
        List<Club> result = new ArrayList<>();
        result.addAll(clubRepository.findAll());

        return result;
    }
    public Club getOne(Long id) {
        return clubRepository.findById(id).orElseThrow();
    }

    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    public Club deleteClub(Long id){
        Club result = clubRepository.findById(id).orElseThrow();
        clubRepository.delete(result);
        return result;
    }
}
