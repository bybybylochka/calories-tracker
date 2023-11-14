package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.dto.RecipeDto;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.service.RecipeService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RecipeResponse {
    private List<RecipeDto> recipes;
}
