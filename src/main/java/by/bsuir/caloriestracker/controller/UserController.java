package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/add-favourite-recipe/{recipeId}")
    public User addRecipeIntoFavourites(@PathVariable long recipeId){
        return userService.addRecipeIntoFavourites(recipeId);
    }
}
