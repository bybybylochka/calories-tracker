package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.ConsumedProduct;
import by.bsuir.caloriestracker.repository.ConsumedProductRepository;
import by.bsuir.caloriestracker.repository.UserRepository;
import by.bsuir.caloriestracker.request.ConsumedProductRequest;
import by.bsuir.caloriestracker.response.ConsumedProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ConsumedProductService {
    private final ConsumedProductRepository consumedProductRepository;
    private final UserService userService;
    private final ProductService productService;

    public ConsumedProduct findById(long id){
        return consumedProductRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Consumed product with this id not found"));
    }
    public ConsumedProductResponse findAll(){
        return new ConsumedProductResponse(consumedProductRepository.findAll());
    }

    public ConsumedProduct addConsumedProduct(ConsumedProductRequest request){
        ConsumedProduct consumedProduct = buildConsumedProduct(request);
        return consumedProductRepository.save(consumedProduct);
    }

    private ConsumedProduct buildConsumedProduct(ConsumedProductRequest request){
        return ConsumedProduct.builder()
                .user(userService.findById(request.getUserId()))
                .product(productService.findById(request.getProductId()))
                .weight(request.getWeight())
                .consumptionTime(LocalDateTime.now())
                .build();
    }
}
