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
public class Editor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fullName;
    @OneToOne
    private AuthorizationData authorizationData;
    @OneToMany(mappedBy = "editor")
    private List<Recipe> recipes;
    @OneToMany(mappedBy = "editor")
    private List<Article> articles;
}
