package by.bsuir.caloriestracker.request;

import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.models.Product;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.util.Map;

@Getter
public class RecipeRequest {
    private String title;
    private int cookingTime;
    private int servingCount;
    private Map<Long, Integer> ingredients;
    private String instruction;
}
