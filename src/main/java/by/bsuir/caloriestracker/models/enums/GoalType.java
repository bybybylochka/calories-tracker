package by.bsuir.caloriestracker.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoalType {
    LOSING_WEIGHT("losing weight", 0.85, 0.35, 0.35, 0.25),
    MAINTAINING_WEIGHT("maintaining weight", 1, 0.3, 0.4, 0.3),
    MASS_GAIN("mass gain", 1.15, 0.4, 0.45, 0.15);

    private final String description;
    private final double metabolismCoefficient;
    private final double proteinsCoefficient;
    private final double carbsCoefficient;
    private final double fatsCoefficient;

    public static GoalType getGoalType (String description){
        for(GoalType type : GoalType.values()) {
            if(type.description.equals(description)){
                return type;
            }
        }
        throw new IllegalArgumentException("Goal type with this description not found");
    }
}
