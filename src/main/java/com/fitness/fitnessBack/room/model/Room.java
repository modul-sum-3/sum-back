package com.fitness.fitnessBack.room.model;


import com.fitness.fitnessBack.category.model.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;



@Entity
@Table(name = "Room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotEmpty(message = "type can't be empty")
    @Column(name = "type", nullable = false)
    private String type;

    @ManyToMany
    private List<Category> categoryList;

    public Room(String type, List<Category> categorySet) {
        this.type = type;
        this.categoryList = categorySet;
    }

    public Room() {

    }
}
