package by.bsuir.caloriestracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int calories;
    private float proteins;
    private float fats;
    private float carbs;
    @OneToMany(mappedBy = "product")
    private List<ConsumedProduct> consumptionHistory;
}
