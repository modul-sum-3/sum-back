package com.fitness.fitnessBack.carnets.model;


import com.fitness.fitnessBack.category.model.Category;
import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = "Carnets")
@Data
@Builder
public class Carnet {
    @Id
    private Long id;
    private Double Price;
    private ZonedDateTime Bought_date;
    private ZonedDateTime Expire_date;
    private List<Category> Access_categories;
}
