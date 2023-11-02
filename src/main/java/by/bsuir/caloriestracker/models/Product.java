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
    @Embedded
    private Kbju kbju;
    @OneToMany(mappedBy = "product")
    private List<ConsumedProduct> consumptionHistory;
}
