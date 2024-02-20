package by.bsuir.caloriestracker.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MealType {
    BREAKFAST("breakfast", 0.3),
    LUNCH("lunch", 0.4),
    DINNER("dinner", 0.2),
    SNACK("snack", 0.1);

    private final String description;
    private final double percentageInDailyMeal;

    public static MealType getMealType (String description){
        for(MealType type : MealType.values()) {
            if(type.description.equals(description)){
                return type;
            }
        }
        throw new IllegalArgumentException("Meal type with this description not found");
    }


}
