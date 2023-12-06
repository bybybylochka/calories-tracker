package by.bsuir.caloriestracker.dto;

import by.bsuir.caloriestracker.models.Norm;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Builder
public class PersonalDataDto {
    private long id;
    private String name;
    private String gender;
    private String activityType;
    private String goalType;
    private Map<LocalDate, Float> weightHistory;
    private float height;
    private LocalDate dateOfBirth;
    private Norm norm;
    private float desiredWeight;
}
