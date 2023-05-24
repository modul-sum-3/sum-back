package com.fitness.fitnessBack.carnets.model;


import com.fitness.fitnessBack.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "Carnets")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Carnet {
    @Id
    private String id;
    private Double price;
    private Long duration;
    private List<Category> access_categories;

    public Carnet(Double price, Long duration, List<Category> access_categories) {
        this.price = price;
        this.duration = duration;
        this.access_categories = access_categories;
    }
}