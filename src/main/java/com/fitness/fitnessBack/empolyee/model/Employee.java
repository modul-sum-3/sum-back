package com.fitness.fitnessBack.empolyee.model;

import com.fitness.fitnessBack.club.model.Club;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "Employee")
@Data
public class Employee {
    @Id
    @GeneratedValue
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

    @ManyToOne
    @MapsId
    @JoinColumn(name="club_id", nullable=false)
    public Club club;
}
