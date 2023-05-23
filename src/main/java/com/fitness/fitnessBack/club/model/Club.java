package com.fitness.fitnessBack.club.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

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

    @NotBlank
    @NotEmpty(message = "city can't be empty")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank
    @NotEmpty(message = "street can't be empty")
    @Column(name = "street", nullable = false)
    private String street;

    public Club(String name, String country, String city, String street) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Club() {

    }
}
