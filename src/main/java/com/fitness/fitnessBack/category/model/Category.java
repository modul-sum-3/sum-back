package com.fitness.fitnessBack.category.model;

import java.util.List;

import com.fitness.fitnessBack.room.model.Room;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "Category")
@Data
public class Category {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @NotEmpty(message = "name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    @JoinColumn(name = "room_id", nullable = false)
    public List<Room> roomList;

    public Category(String name, List<Room> roomList) {
        this.name = name;
        this.roomList = roomList;
    }

    public Category() {

    }
}
