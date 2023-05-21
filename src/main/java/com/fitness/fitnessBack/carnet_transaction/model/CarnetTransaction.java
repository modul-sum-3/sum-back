package com.fitness.fitnessBack.carnet_transaction.model;

import com.fitness.fitnessBack.client.model.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
public class CarnetTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private ZonedDateTime transactionDate;
    private ZonedDateTime expireDate;
    //private Long CarnetID;

    @ManyToOne(optional = false)
    @JoinColumn(name="Client_ID", nullable=false, updatable=false)
    private Client ClientID;
    @NotNull
    private Long carnetID;
    @NotNull
    private double Price;

    public CarnetTransaction() {
    }

    public CarnetTransaction(ZonedDateTime transactionDate, Client clientID, double price) {
        this.transactionDate = transactionDate;
        ClientID = clientID;
        Price = price;

    }
}
