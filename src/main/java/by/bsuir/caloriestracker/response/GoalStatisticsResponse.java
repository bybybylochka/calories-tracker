package by.bsuir.caloriestracker.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class GoalStatisticsResponse {
    private Map<String, Integer> goalStatistics;
}
