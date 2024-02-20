package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.dto.ProductDto;
import by.bsuir.caloriestracker.dto.RecipeDto;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.request.RecipeRequest;
import by.bsuir.caloriestracker.response.RecipeResponse;
import by.bsuir.caloriestracker.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public RecipeDto addRecipe(@RequestBody RecipeRequest request){
        return recipeService.addRecipe(request);
    }
    @GetMapping
    public RecipeResponse getAllRecipes(){
        return recipeService.findAll();
    }
    @GetMapping("/get/author")
    public RecipeResponse getRecipesByEditor(){
        return recipeService.findRecipesByEditor();
    }

    @GetMapping("/allByParams")
    public RecipeResponse getAllRecipesByTitle(@RequestParam String title,
                                               @RequestParam int maxCalories,
                                               @RequestParam boolean shouldSort){
        return recipeService.findListByTitle(title, maxCalories, shouldSort);
    }

    @GetMapping("/sortByLikes")
    public RecipeResponse sortRecipesByLikesQuantity() {return recipeService.sortRecipesByLikesQuantity();}

    @GetMapping("/filterByCalories/{maxCaloriesQuantity}")
    public RecipeResponse filterRecipesByCalories(@PathVariable int maxCaloriesQuantity){
        return recipeService.filterRecipesByCalories(maxCaloriesQuantity);
    }
}
