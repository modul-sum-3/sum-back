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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotEmpty(message = "name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Room> roomList;

    // @NotEmpty(message = "icon can't be empty")
    @Column(name = "icon", nullable = true)
    private byte[] icon;

    public Category(String name, List<Room> roomList, byte[] icon) {
        this.name = name;
        this.roomList = roomList;
        this.icon = icon;
    }

    public Category() {

    }
}
