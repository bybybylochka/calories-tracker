package by.bsuir.caloriestracker.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ActivityType {
    INACTIVE(" inactive"),
    LIGHT_PHYSICAL_ACTIVITY(" light"),
    AVERAGE_PHYSICAL_ACTIVITY(" average"),
    HIGH_PHYSICAL_ACTIVITY(" high");

    private String description;

    public static ActivityType getActivityType(String description){
        for(ActivityType type : ActivityType.values()) {
            if(type.description.equals(description)){
                return type;
            }
        }
        throw new IllegalArgumentException("Activity type with this description not found");
    }
}
