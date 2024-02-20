package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.dto.ConsumedProductDto;
import by.bsuir.caloriestracker.models.ConsumedProduct;
import by.bsuir.caloriestracker.models.Kbju;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.models.enums.MealType;
import by.bsuir.caloriestracker.repository.ConsumedProductRepository;
import by.bsuir.caloriestracker.request.ConsumedProductRequest;
import by.bsuir.caloriestracker.response.ConsumedProductHistoryResponse;
import by.bsuir.caloriestracker.response.ConsumedProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ConsumedProductService {
    private final ConsumedProductRepository consumedProductRepository;
    private final UserService userService;
    private final ProductService productService;

    public ConsumedProduct findById(long id) {
        return consumedProductRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consumed product with this id not found"));
    }

    public ConsumedProductResponse findAll() {
        List<ConsumedProduct> consumedProductList = consumedProductRepository.findAll();
        List<ConsumedProductDto> dtoList = consumedProductList.stream().map(this::toDto).toList();
        return new ConsumedProductResponse(dtoList);
    }

    public ConsumedProductDto addConsumedProduct(ConsumedProductRequest request) {
        ConsumedProduct consumedProduct = buildConsumedProduct(request);
        ConsumedProduct savedConsumedProduct = consumedProductRepository.save(consumedProduct);
        return toDto(savedConsumedProduct);
    }

    private ConsumedProduct buildConsumedProduct(ConsumedProductRequest request) {
        return ConsumedProduct.builder()
                .user(userService.getCurrentUser())
                .product(productService.findById(request.getProductId()))
                .weight(request.getWeight())
                .consumptionTime(LocalDateTime.now())
                .mealType(MealType.getMealType(request.getMealType()))
                .build();
    }

    public List<ConsumedProduct> findConsumedProductsByUser() {
        User user = userService.getCurrentUser();
        return consumedProductRepository.findAllByUser(user);
    }

    public ConsumedProductResponse findConsumedProductByDate(LocalDate date) {
        List<ConsumedProduct> consumedProductList = findConsumedProductsByUser();
        List<ConsumedProduct> consumedProductsByDate = consumedProductList.stream().filter(consumedProduct ->
                consumedProduct.getConsumptionTime().getDayOfYear() == date.getDayOfYear()
        ).toList();
        List<ConsumedProductDto> consumedProductDtoList = consumedProductsByDate.stream().map(this::toDto).toList();
        return new ConsumedProductResponse(consumedProductDtoList);
    }

    public ConsumedProductDto toDto(ConsumedProduct consumedProduct) {
        return ConsumedProductDto.builder()
                .id(consumedProduct.getId())
                .productName(consumedProduct.getProduct().getName())
                .consumptionTime(consumedProduct.getConsumptionTime())
                .kbju(calculateKbju(consumedProduct))
                .weight(consumedProduct.getWeight())
                .mealType(consumedProduct.getMealType().getDescription())
                .build();
    }

    private Kbju calculateKbju(ConsumedProduct consumedProduct) {
        Kbju kbju = consumedProduct.getProduct().getKbju();
        return Kbju.builder()
                .calories(kbju.getCalories() * consumedProduct.getWeight() / 100)
                .carbohydrates(kbju.getCarbohydrates() * consumedProduct.getWeight() / 100)
                .fats(kbju.getFats() * consumedProduct.getWeight() / 100)
                .proteins(kbju.getProteins() * consumedProduct.getWeight() / 100)
                .build();
    }

    public ConsumedProductHistoryResponse findConsumedProductHistory() {
        final int daysQuantity = 14;
        LocalDate startDate = LocalDate.now().minusDays(daysQuantity);
        Map<LocalDate, ConsumedProductResponse> consumptionHistory = new HashMap<>();
        for (int i = 0; i<daysQuantity; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            consumptionHistory.put(currentDate, findConsumedProductByDate(currentDate));
        }
        return new ConsumedProductHistoryResponse(consumptionHistory);
    }

    public ConsumedProductDto deleteConsumedProduct(long consumedProductId) {
        ConsumedProduct consumedProduct = findById(consumedProductId);
        consumedProductRepository.delete(consumedProduct);
        return toDto(consumedProduct);
    }

    public void updateConsumedProduct(long consumedProductId, int newWeight) {
        consumedProductRepository.updateWeight(consumedProductId, newWeight);
    }
}
