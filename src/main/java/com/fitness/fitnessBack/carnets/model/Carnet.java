package com.fitness.fitnessBack.carnets.model;


import com.fitness.fitnessBack.category.model.Category;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "Carnets")
@NoArgsConstructor
@Data
public class Carnet {
    @Id
    private Long id;
    private Double price;
    private Long duration;
    private List<Category> access_categories;
}
