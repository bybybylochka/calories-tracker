package by.bsuir.caloriestracker.dto;

import by.bsuir.caloriestracker.models.Kbju;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ConsumedProductDto {
    private String productName;
    private LocalDateTime consumptionTime;
    private int weight;
    private Kbju kbju;
}
