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
    @NotEmpty(message = "location can't be empty")
    @Column(name = "location", nullable = false)
    private String location;

    @NotBlank
    @NotEmpty(message = "zip-code can't be empty")
    @Column(name = "zip-code", nullable = false)
    private String zip;

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

    public Club(String name, String location, String city, String street,String zip,List<Room> rooms) {
        this.rooms = rooms;
        this.name = name;
        this.location = location;
        this.zip = zip;
        this.city = city;
        this.street = street;
        this.openTime = "8:00";
        this.closeTime = "23:45";
    }

    public Club() {

    }
}
