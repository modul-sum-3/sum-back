package com.fitness.fitnessBack.room.controller;

import com.fitness.fitnessBack.room.model.Room;
import com.fitness.fitnessBack.room.service.RoomService;

import jakarta.validation.Valid;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Value
@CrossOrigin
@RequestMapping(value = "/rooms", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoomController {
    RoomService roomService;

    @GetMapping
    public List<Room> findAll() {
        return roomService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Room> findOne(@PathVariable(value = "id") Long id) {
        return roomService.getOne(id);
    }

    @PostMapping
    public Room saveRoom(@Valid @RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @DeleteMapping("/{id}")
    public Room deleteRoom(@PathVariable(value = "id") Long id) {
        return roomService.deleteRoom(id);
    }
}
