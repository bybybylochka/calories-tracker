package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.request.RecipeRequest;
import by.bsuir.caloriestracker.response.RecipeResponse;
import by.bsuir.caloriestracker.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping("/add")
    public Recipe addRecipe(RecipeRequest request){
        return recipeService.addRecipe(request);
    }
    @GetMapping("")
    public RecipeResponse getAllRecipes(RecipeRequest request){
        return recipeService.findAll();
    }
    @GetMapping("/get/author")
    public RecipeResponse getRecipesByEditor(){
        return recipeService.findRecipesByEditor();
    }
}
