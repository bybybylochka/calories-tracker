package by.bsuir.caloriestracker.models;

import by.bsuir.caloriestracker.models.enums.ActivityType;
import by.bsuir.caloriestracker.models.enums.Gender;
import by.bsuir.caloriestracker.models.enums.GoalType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private ActivityType activityType;
    @Enumerated(value = EnumType.STRING)
    private GoalType goalType;
    @OneToMany(mappedBy = "personalData")
    private List<WeightHistory> weightHistory;
    private float height;
    private LocalDate dateOfBirth;
    @Embedded
    private Norm norm;
    private float desiredWeight;
}
