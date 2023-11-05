package by.bsuir.caloriestracker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ConsumedProductHistoryResponse {
    private Map<LocalDate, ConsumedProductResponse> consumptionHistory;
}
