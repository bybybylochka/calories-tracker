package by.bsuir.caloriestracker.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EditorDto {
    private long id;
    private String fullName;
    private int recipesCount;
    private int articlesCount;
    private int totalRecipesLikesCount;
    private double weekActivity;
}
