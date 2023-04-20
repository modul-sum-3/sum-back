package com.fitness.fitnessBack.user.controller;

import com.fitness.fitnessBack.user.model.User;
import com.fitness.fitnessBack.user.service.UserService;
import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Value
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable(value = "id") Long id) {
        return userService.getOne(id);
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable(value = "id") Long id) {
        return userService.deleteUser(id);
    }
}
