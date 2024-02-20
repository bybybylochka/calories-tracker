package by.bsuir.caloriestracker.request;

import lombok.Getter;

@Getter
public class ConsumedProductRequest {
    private long productId;
    private String mealType;
    private int weight;
}
