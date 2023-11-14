package by.bsuir.caloriestracker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ConsumedWaterHistoryResponse {
    private Map<LocalDate, ConsumedWaterResponse> consumptionHistory;
}
