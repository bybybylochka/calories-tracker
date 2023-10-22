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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private AuthorizationData authorizationData;
    @OneToOne
    private PersonalData personalData;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ConsumedProduct> consumptionHistory;
    @ManyToMany
    private List<Recipe> favouriteRecipes;
}
