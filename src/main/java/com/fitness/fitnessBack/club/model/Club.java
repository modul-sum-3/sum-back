package com.fitness.fitnessBack.club.model;

import com.fitness.fitnessBack.room.model.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Club")
@Data
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotEmpty(message = "name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @NotEmpty(message = "country can't be empty")
    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany
    private List<Room> rooms;

    @NotBlank
    @NotEmpty(message = "city can't be empty")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank
    @NotEmpty(message = "street can't be empty")
    @Column(name = "street", nullable = false)
    private String street;

    @NotBlank
    @NotEmpty(message = "Open time can't be empty")
    private String openTime;

    @NotBlank
    @NotEmpty(message = "Close time can't be empty")
    private String closeTime;

    public Club(String name, String country, String city, String street,List<Room> rooms) {
        this.rooms = rooms;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.openTime = "8:00";
        this.closeTime = "23:45";
    }

    public Club() {

    }
}
