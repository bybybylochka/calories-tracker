package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.dto.ConsumedProductDto;
import by.bsuir.caloriestracker.models.Kbju;
import lombok.Getter;

import java.util.List;

@Getter
public class ConsumedProductResponse {
    private final List<ConsumedProductDto> consumedProducts;
    private final Kbju totalKbju;

    public ConsumedProductResponse(List<ConsumedProductDto> consumedProductDtoList) {
        this.consumedProducts = consumedProductDtoList;
        this.totalKbju = calculateTotalKbju(consumedProductDtoList);
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
