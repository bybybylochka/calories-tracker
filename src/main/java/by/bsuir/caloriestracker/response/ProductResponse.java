package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.models.Product;
import by.bsuir.caloriestracker.service.ProductService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductResponse {
    private List<Product> products;
}
