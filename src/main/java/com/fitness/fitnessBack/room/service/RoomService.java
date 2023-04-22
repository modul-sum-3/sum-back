package com.fitness.fitnessBack.room.service;

import com.fitness.fitnessBack.room.model.Room;
import com.fitness.fitnessBack.room.repository.RoomRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Value
public class RoomService {
    RoomRepository roomRepository;

    public List<Room> getAll() {
        List<Room> result = new ArrayList<>();
        result.addAll(roomRepository.findAll());

        return result;
    }

    public Room getOne(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room deleteRoom(Long id) {
        Room result = roomRepository.findById(id).orElseThrow();
        roomRepository.delete(result);
        return result;
    }
}
