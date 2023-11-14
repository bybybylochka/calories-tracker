package by.bsuir.caloriestracker.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeStatisticsDto {
    private long id;
    private String recipeName;
    private String editorName;
    private int likesQuantity;
}
