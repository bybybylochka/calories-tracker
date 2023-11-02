package by.bsuir.caloriestracker.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ActivityType {
    INACTIVE(" inactive", 1),
    LIGHT_PHYSICAL_ACTIVITY(" light", 1.3),
    AVERAGE_PHYSICAL_ACTIVITY(" average", 1.5),
    HIGH_PHYSICAL_ACTIVITY(" high", 1.7);

    private final String description;
    private final double activityCoefficient;

    public static ActivityType getActivityType(String description){
        for(ActivityType type : ActivityType.values()) {
            if(type.description.equals(description)){
                return type;
            }
        }
        throw new IllegalArgumentException("Activity type with this description not found");
    }
}
