package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.models.enums.GoalType;
import by.bsuir.caloriestracker.repository.UserRepository;
import by.bsuir.caloriestracker.response.GoalStatisticsResponse;
import by.bsuir.caloriestracker.response.RegistrationStatisticsResponse;
import by.bsuir.caloriestracker.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RecipeService recipeService;

    public User findById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with this id not found"));
    }
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User with such username not found"));
    }

    public UserResponse findAll(){
        return new UserResponse(userRepository.findAll());
    }

    public long getTotalUsersCount() {
        return userRepository.count();
    }

    public long getTodayRegisteredUsersCount() {
        return userRepository.findAll().stream()
                .filter(user -> user.getAuthorizationData().getCreatedAt().equals(LocalDate.now()))
                .count();
    }

    public RegistrationStatisticsResponse getRegistrationStatistics() {
        Map<LocalDate, List<User>> usersByRegistrationDate = userRepository.findAll().stream()
                .collect(Collectors.groupingBy(user -> user.getAuthorizationData().getCreatedAt()));
        Map<LocalDate, Integer> usersCountByRegistrationDate = usersByRegistrationDate.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
        return new RegistrationStatisticsResponse(usersCountByRegistrationDate);
    }

    public GoalStatisticsResponse getGoalStatistics() {
        Map<GoalType, List<User>> usersByGoal = userRepository.findAll().stream()
                .collect(Collectors.groupingBy(user -> user.getPersonalData().getGoalType()));
        Map<String, Integer> usersCountByGoal = usersByGoal.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getDescription() , entry -> entry.getValue().size()));
        return new GoalStatisticsResponse(usersCountByGoal);
    }

    public PersonalData findPersonalData(User user) {
        return user.getPersonalData();
    }

    public void addPersonalData(PersonalData personalData) {
        User user = getCurrentUser();
        user.setPersonalData(personalData);
        userRepository.save(user);
    }
    
    public User addRecipeIntoFavourites(long recipeId) {
        User currentUser = getCurrentUser();
        Recipe recipe = recipeService.findById(recipeId);
        currentUser.getFavouriteRecipes().add(recipe);
        recipe.getLikedUserList().add(currentUser);
        recipeService.save(recipe);
        return userRepository.save(currentUser);
    }

    public List<Recipe> getFavouriteRecipes() {
        User currentUser = getCurrentUser();
        return currentUser.getFavouriteRecipes();
    }

    public User getCurrentUser() {
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) currentAuthentication.getPrincipal()).getUsername();
        return findByUsername(username);
    }

}
