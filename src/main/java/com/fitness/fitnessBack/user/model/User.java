package com.fitness.fitnessBack.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @NotEmpty(message = "password can't be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @Email
    @NotBlank
    @NotEmpty(message = "email can't be empty")
    @Column(name = "email_address", nullable = false)
    private String email;

    @NotBlank
    @NotEmpty(message = "role can't be empty")
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Past
    private LocalDate date_of_registration;

    public enum UserRole {
        CLIENT,
        TRAINER,
        EMPLOYEE
    }

    public User(String password, String email, UserRole role, LocalDate date_of_registration) {
        this.password = password;
        this.email = email;
        this.role = role;
        this.date_of_registration = date_of_registration;
    }

    public User() {

    }
}
