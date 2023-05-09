package com.fitness.fitnessBack.empolyee.model;

import com.fitness.fitnessBack.club.model.Club;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Employee")
@Data
public class Employee {
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

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    public Club club;

    public Employee(String first_name, String last_name, String email, String phone_number, LocalDate date_of_birth,
            Club club) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.date_of_birth = date_of_birth;
        this.club = club;
    }

    public Employee() {

    }
}
