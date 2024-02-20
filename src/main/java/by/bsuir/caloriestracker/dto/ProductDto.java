package by.bsuir.caloriestracker.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDto {
    private long id;
    private String name;
    private int calories;
    private int proteins;
    private int fats;
    private int carbs;
}
