package com.fitness.fitnessBack.category.model;

import java.util.List;

import com.fitness.fitnessBack.room.model.Room;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @ManyToMany
    @NotEmpty
    @NotNull
    private List<Room> roomList;

    // @NotEmpty(message = "icon can't be empty")
    @Lob
    @Column(name = "icon", nullable = true)
    private byte[] icon;

    public Category(String name,List<Room> rooms,byte[] icon) {
        this.name = name;
        this.roomList = rooms;
        this.icon = icon;
    }

    public Category() {

    }
}
