package com.fitness.fitnessBack.VisitRanking.model;

import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.club.model.Club;
import com.fitness.fitnessBack.training.model.Training;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Table(name = "VisitRanking")
@Data
public class VisitRanking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @PastOrPresent
    @NotNull
    ZonedDateTime Start_visit;


    @PastOrPresent
    @Nullable
    ZonedDateTime End_visit;

    @ManyToOne
    @JoinColumn(name = "ClientID", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "TrainingID")
    private Training training;

    @ManyToOne
    @JoinColumn(name = "ClubID", nullable = false)
    private Club club;

    @Enumerated(EnumType.STRING)
    @Nullable
    private Rating rating;

    public VisitRanking(ZonedDateTime start_visit, Client client, Training training, Club club, Rating rating) {
        Start_visit = start_visit;
        this.client = client;
        this.training = training;
        this.club = club;
        this.rating = rating;
        this.End_visit = ZonedDateTime.now();
    }

    public VisitRanking() {
    }
}
