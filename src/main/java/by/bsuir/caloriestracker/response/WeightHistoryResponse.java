package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.models.WeightHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class WeightHistoryResponse {
    List<WeightHistory> weightHistoryList;
}
