package by.bsuir.caloriestracker.request;

import lombok.Getter;

@Getter
public class ConsumedProductRequest {
    private long userId;
    private long productId;
    private float weight;
}
