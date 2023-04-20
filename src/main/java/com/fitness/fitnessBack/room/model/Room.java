package com.fitness.fitnessBack.room.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "Room")
@Data
public class Room {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @NotEmpty(message = "type can't be empty")
    @Column(name = "type", nullable = false)
    private String type;

    @NotBlank
    @NotEmpty(message = "icon can't be empty")
    @Column(name = "icon", nullable = false)
    private List<String> icon;

    public Room(String type, List<String> icon) {
        this.type = type;
        this.icon = icon;
    }

    public Room() {

    }
}
