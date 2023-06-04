package com.fitness.fitnessBack.room.service;

import com.fitness.fitnessBack.category.repository.CategoryRepository;
import com.fitness.fitnessBack.club.model.Club;
import com.fitness.fitnessBack.club.repository.ClubRepository;
import com.fitness.fitnessBack.room.model.Room;
import com.fitness.fitnessBack.room.repository.RoomRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Value
public class RoomService {
    CategoryRepository categoryRepository;
    ClubRepository clubRepository;
    RoomRepository roomRepository;

    public List<Room> getAll() {
        List<Room> result = new ArrayList<>();
        result.addAll(roomRepository.findAll());

        return result;
    }

    public Optional<Room> getOne(Long id) {
        return roomRepository.findById(id);
    }

    public Room saveRoom(Room room, Long clubId) {
        Club club = clubRepository.findById(clubId).orElseThrow();
        club.getRooms().add(room);
        Room result = roomRepository.save(room);
        clubRepository.save(club);
        return result;
    }
    public Room addCategory(Long roomId,Long categoryId) {
        Room result = roomRepository.findById(roomId).orElseThrow();
        result.getCategoryList().add(categoryRepository.findById(categoryId).orElseThrow());
        return roomRepository.save(result);
    }

    public Room deleteRoom(Long id) {
        Room result = roomRepository.findById(id).orElseThrow();
        roomRepository.delete(result);
        return result;
    }
}
