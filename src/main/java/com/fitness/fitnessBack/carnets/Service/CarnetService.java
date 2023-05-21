package com.fitness.fitnessBack.carnets.Service;

import com.fitness.fitnessBack.carnets.model.Carnet;
import com.fitness.fitnessBack.carnets.repository.CarnetRepository;
import lombok.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Value
public class CarnetService {
    CarnetRepository carnetRepository;
    public List<Carnet> getAll() {
        return carnetRepository.findAll(Sort.by(Sort.Direction.ASC,"price"));
    }
    public Optional<Carnet> getOne(String id) {
        if(carnetRepository.findById(id).isPresent()) {
            return carnetRepository.findById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doesn't exist carnet with that id!");
        }
    }
    public Carnet createCarnet(Carnet carnet){
        return carnetRepository.save(carnet);
    }

}
