package by.bsuir.caloriestracker.dto;

import by.bsuir.caloriestracker.models.Kbju;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class RecipeDto {
    private long id;
    private String publicationTime;
    private String title;
    private int cookingTime;
    private int servingCount;
    private Map<String, Integer> ingredients;
    private String instruction;
    private String editorName;
    private Kbju kbju;
}
