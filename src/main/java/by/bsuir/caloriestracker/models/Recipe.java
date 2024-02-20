package by.bsuir.caloriestracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private Map<Product, Integer> ingredients = new HashMap<>();
    private String instruction;
    @ManyToOne
    private Editor editor;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_favourite_recipes")
    private List<User> likedUserList = new ArrayList<>();
}
