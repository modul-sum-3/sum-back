package com.fitness.fitnessBack.client.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

enum Gender {
    FEMALE,
    MALE,
    UNKNOWN
}

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @NotEmpty(message = "first name must not be empty")
    private String first_name;

    @NotBlank
    @NotEmpty(message = "last name must not be empty")
    private String last_name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Past
    private LocalDate Date_of_birth;

    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "must be a number or null")
    private String phoneNumber;

    @Email
    @NotBlank
    @NotNull
    private String email;

    @PositiveOrZero
    @NotNull
    private Double Balance;

    public Client(String first_name, String last_name, Gender gender, String phoneNumber, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client() {

    }
}
