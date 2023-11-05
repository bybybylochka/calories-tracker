package by.bsuir.caloriestracker.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConsumedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime consumptionTime;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    private int weight;
}
