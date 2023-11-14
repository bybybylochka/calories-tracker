package by.bsuir.caloriestracker.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ConsumedWaterDto {
    private long id;
    private String consumptionTime;
    private int volume;
    private long userId;
}
