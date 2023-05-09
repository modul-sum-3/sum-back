package com.fitness.fitnessBack.trainer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Trainer")
@Data
public class Trainer {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

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

    public Trainer(String first_name, String last_name, String email, String phone_number, LocalDate date_of_birth) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.date_of_birth = date_of_birth;
    }

    public Trainer() {

    }
}
