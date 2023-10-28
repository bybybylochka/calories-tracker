package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.models.ConsumedProduct;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class ConsumedProductResponse {
    private List<ConsumedProduct> consumedProducts;
}
