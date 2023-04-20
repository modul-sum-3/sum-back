package com.fitness.fitnessBack.club.model;

import com.fitness.fitnessBack.room.model.Room;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "Club")
@Data
public class Club {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @NotEmpty(message = "name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    public Room room;

    public Club(String name, Room room) {
        this.name = name;
        this.room = room;
    }

    public Club() {

    }
}
