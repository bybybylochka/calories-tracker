package by.bsuir.caloriestracker.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GoalType {
    LOSING_WEIGHT(" losing weight"),
    MASS_GAIN(" mass gain"),
    MAINTAINING_WEIGHT(" maintaining weight");

    private String description;

    public static GoalType getGoalType (String description){
        for(GoalType type : GoalType.values()) {
            if(type.description.equals(description)){
                return type;
            }
        }
        throw new IllegalArgumentException("Goal type with this description not found");
    }
}
