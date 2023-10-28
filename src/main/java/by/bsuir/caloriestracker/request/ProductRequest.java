package by.bsuir.caloriestracker.request;

import lombok.Getter;

@Getter
public class ProductRequest {
    private String name;
    private int calories;
    private float proteins;
    private float fats;
    private float carbs;
}
