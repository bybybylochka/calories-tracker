package by.bsuir.caloriestracker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class Kbju {
    private int calories;
    private int proteins;
    private int carbohydrates;
    private int fats;
}
