package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.response.EditorResponse;
import by.bsuir.caloriestracker.response.GoalStatisticsResponse;
import by.bsuir.caloriestracker.response.RecipeStatisticsResponse;
import by.bsuir.caloriestracker.response.RegistrationStatisticsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticsService {
    private final UserService userService;
    private final EditorService editorService;
    private final RecipeService recipeService;
    public long getTotalUsersCount() {
        return userService.getTotalUsersCount();
    }
    public long getTodayUsersCount() {
        return userService.getTodayRegisteredUsersCount();
    }
    public RegistrationStatisticsResponse getRegistrationStatistics() {
        return userService.getRegistrationStatistics();
    }
    public GoalStatisticsResponse getGoalStatistics() {
        return userService.getGoalStatistics();
    }
    public EditorResponse getEditorStatistics() {
        return editorService.findAll();
    }
    public RecipeStatisticsResponse getRecipeStatistics() {
        return recipeService.getRecipeStatistics();
    }
}
