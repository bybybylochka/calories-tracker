package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.dto.ConsumedWaterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ConsumedWaterResponse {
    private List<ConsumedWaterDto> consumedWaterList;
    private int totalVolume;
}
