package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.response.GoalStatisticsResponse;
import by.bsuir.caloriestracker.response.RegistrationStatisticsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatisticsService {
    private final UserService userService;
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
}
