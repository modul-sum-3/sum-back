package com.fitness.fitnessBack.trainer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Trainer")
@Data
public class Trainer {
    @Id
    private long id;

    @NotBlank
    @NotEmpty(message = "firstname can't be empty")
    @Column(name = "first_name", nullable = false)
    private String first_name;

    @NotBlank
    @NotEmpty(message = "lastname can't be empty")
    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Email
    @NotBlank
    @NotEmpty(message = "email can't be empty")
    @Column(name = "email_address", nullable = false)
    private String email;

    private String phone_number;

    @Past
    private LocalDate date_of_birth;
}