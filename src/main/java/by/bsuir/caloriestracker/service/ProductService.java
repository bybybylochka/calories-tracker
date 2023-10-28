package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.models.Product;
import by.bsuir.caloriestracker.repository.EditorRepository;
import by.bsuir.caloriestracker.repository.ProductRepository;
import by.bsuir.caloriestracker.request.ProductRequest;
import by.bsuir.caloriestracker.response.EditorResponse;
import by.bsuir.caloriestracker.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findById(long id){
        return productRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Product with this id not found"));
    }
    public ProductResponse findAll(){
        return new ProductResponse(productRepository.findAll());
    }

    public Product addProduct(ProductRequest request){
        Product product = buildProduct(request);
        return productRepository.save(product);
    }

    private Product buildProduct(ProductRequest request){
        return Product.builder()
                .name(request.getName())
                .calories(request.getCalories())
                .proteins(request.getProteins())
                .carbs(request.getCarbs())
                .fats(request.getFats())
                .build();
    }
}
