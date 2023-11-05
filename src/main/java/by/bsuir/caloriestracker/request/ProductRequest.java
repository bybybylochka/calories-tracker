package by.bsuir.caloriestracker.request;

import lombok.Getter;

@Getter
public class ProductRequest {
    private String name;
    private int calories;
    private int proteins;
    private int fats;
    private int carbs;
}
