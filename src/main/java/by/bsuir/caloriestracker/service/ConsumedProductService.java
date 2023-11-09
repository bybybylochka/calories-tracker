package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.dto.ConsumedProductDto;
import by.bsuir.caloriestracker.models.ConsumedProduct;
import by.bsuir.caloriestracker.models.Kbju;
import by.bsuir.caloriestracker.models.User;
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

    public ConsumedProduct addConsumedProduct(ConsumedProductRequest request) {
        ConsumedProduct consumedProduct = buildConsumedProduct(request);
        return consumedProductRepository.save(consumedProduct);
    }

    private ConsumedProduct buildConsumedProduct(ConsumedProductRequest request) {
        return ConsumedProduct.builder()
                .user(userService.findById(request.getUserId()))
                .product(productService.findById(request.getProductId()))
                .weight(request.getWeight())
                .consumptionTime(LocalDateTime.now())
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
                .productName(consumedProduct.getProduct().getName())
                .consumptionTime(consumedProduct.getConsumptionTime())
                .kbju(calculateKbju(consumedProduct))
                .weight(consumedProduct.getWeight())
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

//    public ConsumedProductResponse findConsumedProductBy(long userId, LocalDate date) {
//        List<ConsumedProduct> consumedProductByDate = findConsumedProductByDate(userId, LocalDate.now());
//        List<ConsumedProductDto> consumedProductDtoList = consumedProductByDate.stream().map(this::toDto).toList();
//        return new ConsumedProductResponse(consumedProductDtoList);
//    }

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
}
