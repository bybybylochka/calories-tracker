package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.response.EditorResponse;
import by.bsuir.caloriestracker.response.GoalStatisticsResponse;
import by.bsuir.caloriestracker.response.RecipeStatisticsResponse;
import by.bsuir.caloriestracker.response.RegistrationStatisticsResponse;
import by.bsuir.caloriestracker.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/user/count")
    public long getTotalUserCount() {
        return statisticsService.getTotalUsersCount();
    }

    @GetMapping("/user/count/today")
    public long getTodayRegisteredUserCount() {
        return statisticsService.getTodayUsersCount();
    }

    @GetMapping("/user/registration")
    public RegistrationStatisticsResponse getUserRegistrationStatistics() {
        return statisticsService.getRegistrationStatistics();
    }

    @GetMapping("/user/goal")
    public GoalStatisticsResponse getUserGoalsStatistics() {
        return statisticsService.getGoalStatistics();
    }

    @GetMapping("/editor")
    public EditorResponse getEditorStatistics() {
        return statisticsService.getEditorStatistics();
    }

    @GetMapping("/recipe")
    public RecipeStatisticsResponse getRecipeStatistics() {
        return statisticsService.getRecipeStatistics();
    }
}
