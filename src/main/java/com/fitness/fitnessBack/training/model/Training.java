package com.fitness.fitnessBack.training.model;

import com.fitness.fitnessBack.category.model.Category;
import com.fitness.fitnessBack.client.model.Client;
import com.fitness.fitnessBack.club.model.Club;
import com.fitness.fitnessBack.room.model.Room;
import com.fitness.fitnessBack.trainer.model.Trainer;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Data
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Nullable
    private Set<Client> clients;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="club_id", nullable = false)
    public Club club;

    @PositiveOrZero
    @NotNull
    private int Amount;

    @FutureOrPresent
    @NotNull
    private ZonedDateTime StartDate;


    public Training(Club club,Room room, Trainer trainer, Category category, int amount, ZonedDateTime date) {
        this.room = room;
        this.club = club;
        this.trainer = trainer;
        this.category = category;
        Amount = amount;
        StartDate = date;
    }

    public Training() {

    }
}
