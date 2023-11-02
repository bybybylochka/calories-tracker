package by.bsuir.caloriestracker.models;

import by.bsuir.caloriestracker.models.builder.KbjuBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Kbju {
    private int calories;
    private double proteins;
    private double carbohydrates;
    private double fats;

    public static KbjuBuilder builder() {
        return new KbjuBuilder();
    }

}
