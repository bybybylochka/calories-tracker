package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.PersonalData;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.repository.UserRepository;
import by.bsuir.caloriestracker.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

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
