package by.bsuir.caloriestracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime publicationTime;
    private String title;
    private int cookingTime;
    private int servingCount;
    @ElementCollection
    @CollectionTable(name = "recipe_product")
    @MapKeyColumn(name = "product_id")
    @Column(name = "weight")
    private Map<Product, Float> ingredients;
    private String instruction;
    @ManyToOne
    private Editor editor;
}
