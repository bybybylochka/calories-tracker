package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.dto.RecipeStatisticsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RecipeStatisticsResponse {
    private List<RecipeStatisticsDto> recipeStatistics;
}
