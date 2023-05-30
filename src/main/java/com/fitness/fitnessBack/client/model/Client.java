package com.fitness.fitnessBack.client.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @NotBlank
    @NotEmpty(message = "first name must not be empty")
    private String first_name;

    @NotBlank
    @NotEmpty(message = "last name must not be empty")
    private String last_name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Past
    private LocalDate date_of_birth;

    @NotNull(message = "must be a number")
    private String phoneNumber;

    @Email
    @NotBlank
    @NotNull
    private String email;

    @PositiveOrZero
    @NotNull
    private Double Balance;

    public Client(String first_name, String last_name, String phoneNumber, String email,LocalDate date_of_birth) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = Gender.MALE;
        this.date_of_birth = date_of_birth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.Balance = 0.0;
    }

}
