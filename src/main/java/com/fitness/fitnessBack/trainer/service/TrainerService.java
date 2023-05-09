package com.fitness.fitnessBack.trainer.service;

import com.fitness.fitnessBack.trainer.model.Trainer;
import com.fitness.fitnessBack.trainer.model.TrainerPass;
import com.fitness.fitnessBack.trainer.repository.TrainerRepository;
import com.fitness.fitnessBack.user.model.Role;
import com.fitness.fitnessBack.user.model.User;
import com.fitness.fitnessBack.user.repository.UserRepository;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class TrainerService {
    TrainerRepository trainerRepository;
    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public List<Trainer> getAll() {
        List<Trainer> result = new ArrayList<>();
        result.addAll(trainerRepository.findAll());

        return result;
    }
    public Trainer getOne(Long id) {
        return trainerRepository.findById(id).orElseThrow();
    }

    public Trainer saveTrainer(TrainerPass trainer) {

        if(userRepository.findByEmail(trainer.getTrainer().getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists!");
        }
        Trainer saved = trainerRepository.save(trainer.getTrainer());
        User user = User.builder()
                .id(saved.getId())
                .role(Role.TRAINER)
                .password(passwordEncoder.encode(trainer.getPassword()))
                .email(trainer.getTrainer().getEmail())
                .build();
        userRepository.save(user);
        return saved;
    }

    public Trainer deleteTrainer(Long id){
        Trainer result = trainerRepository.findById(id).orElseThrow();
        trainerRepository.delete(result);
        return result;
    }
}
