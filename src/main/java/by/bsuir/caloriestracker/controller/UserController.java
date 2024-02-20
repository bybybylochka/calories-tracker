package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.dto.RecipeDto;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/add-favourite-recipe/{recipeId}")
    public RecipeDto addRecipeIntoFavourites(@PathVariable long recipeId){
        return userService.addRecipeIntoFavourites(recipeId);
    }

    @GetMapping("/favourite-recipes")
    public List<RecipeDto> getFavouriteRecipes(){
        return userService.getFavouriteRecipes();
    }
}
