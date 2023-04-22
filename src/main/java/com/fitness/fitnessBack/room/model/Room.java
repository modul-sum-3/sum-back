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

    //@NotEmpty(message = "icon can't be empty")
    @Column(name = "icon", nullable = true)
    private byte[] icon;

    @ManyToOne
    @JoinColumn(name="club_id", nullable = false)
    public Club club;


    public Room(String type, byte[] icon,Club club) {
        this.type = type;
        this.icon = icon;
        this.club = club;
    }

    public Room() {

    }
}
