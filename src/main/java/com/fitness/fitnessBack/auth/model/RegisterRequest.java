package com.fitness.fitnessBack.auth.model;


import com.fitness.fitnessBack.client.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String first_name;
    private String last_name;
    private Gender gender;
    private String email;
    private LocalDate date_of_birth;
    private String phoneNumber;
    private String password;
}
