package com.fitness.fitnessBack.trainer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerPass {
    Trainer trainer;
    String password;
}
