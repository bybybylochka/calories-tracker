package by.bsuir.caloriestracker.models;

import by.bsuir.caloriestracker.models.builder.KbjuBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Kbju {
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;

    public static KbjuBuilder builder() {
        return new KbjuBuilder();
    }
}
