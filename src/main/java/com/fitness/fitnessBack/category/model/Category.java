package com.fitness.fitnessBack.category.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "Category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    @NotEmpty(message = "name can't be empty")
    @Column(name = "name", nullable = false)
    private String name;
    // @NotEmpty(message = "icon can't be empty")
    @Lob
    @Column(name = "icon", nullable = true)
    private byte[] icon;

    public Category(String name,byte[] icon) {
        this.name = name;
        this.icon = icon;
    }

    public Category() {

    }
}
