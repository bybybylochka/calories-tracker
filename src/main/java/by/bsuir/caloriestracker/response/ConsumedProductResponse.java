package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.dto.ConsumedProductDto;
import by.bsuir.caloriestracker.models.Kbju;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ConsumedProductResponse {
    private final List<ConsumedProductDto> consumedProducts;
    private final Kbju totalKbju;
    private Map<String, Integer> caloriesByMealType;

    public ConsumedProductResponse(List<ConsumedProductDto> consumedProductDtoList) {
        this.consumedProducts = consumedProductDtoList;
        this.totalKbju = calculateTotalKbju(consumedProductDtoList);
        calculateConsumedByMealType(consumedProducts);
    }

    private void calculateConsumedByMealType(List<ConsumedProductDto> consumedProducts) {
        Map<String, List<ConsumedProductDto>> collected = consumedProducts.stream()
                .collect(Collectors.groupingBy(ConsumedProductDto::getMealType));
        this.caloriesByMealType =  collected.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToInt(consumedProduct -> consumedProduct.getKbju().getCalories())
                                .reduce(0, Integer::sum)
                        )
                );
    }


    private Kbju calculateTotalKbju(List<ConsumedProductDto> consumedProductDtoList) {
        List<Kbju> kbjuList = consumedProductDtoList.stream().map(ConsumedProductDto::getKbju).toList();
        return Kbju.builder()
                .calories(kbjuList.stream().map(Kbju::getCalories).reduce(0, Integer::sum))
                .proteins(kbjuList.stream().map(Kbju::getProteins).reduce(0, Integer::sum))
                .fats(kbjuList.stream().map(Kbju::getFats).reduce(0, Integer::sum))
                .carbohydrates(kbjuList.stream().map(Kbju::getCarbohydrates).reduce(0, Integer::sum))
                .build();
    }
}
