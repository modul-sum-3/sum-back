package com.fitness.fitnessBack.user.service;

import com.fitness.fitnessBack.user.model.User;
import com.fitness.fitnessBack.user.repository.UserRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class UserService {
    UserRepository userRepository;

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        result.addAll(userRepository.findAll());

        return result;
    }

    public User getOne(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(Long id) {
        User result = userRepository.findById(id).orElseThrow();
        userRepository.delete(result);
        return result;
    }
}
