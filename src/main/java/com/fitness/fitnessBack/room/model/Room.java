package com.fitness.fitnessBack.room.model;

import com.fitness.fitnessBack.club.model.Club;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    public Club club;

    public Room(String type, Club club) {
        this.type = type;
        this.club = club;
    }

    public Room() {

    }
}
