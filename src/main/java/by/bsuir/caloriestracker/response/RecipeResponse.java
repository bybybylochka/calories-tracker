package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.dto.RecipeDto;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RecipeResponse {
    private List<RecipeDto> recipes;
}
