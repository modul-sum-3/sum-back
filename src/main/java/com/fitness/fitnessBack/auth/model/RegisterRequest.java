package com.fitness.fitnessBack.auth.model;


import com.fitness.fitnessBack.client.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    Client client;
    private String password;
}
