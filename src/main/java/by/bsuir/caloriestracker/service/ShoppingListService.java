package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.Product;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.request.ShoppingListRequest;
import by.bsuir.caloriestracker.response.ShoppingListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ShoppingListService {
    private final RecipeService recipeService;
    public ShoppingListResponse createShoppingList(ShoppingListRequest request){
        List<Long> recipes = request.getRecipes();
        List<Recipe> listRecipe = recipes.stream().map(recipe -> recipeService.findById(recipe)).toList();
        Map<String, Integer> ingredients = new HashMap<>();
        listRecipe.forEach(recipe -> addIngredientsFromRecipe(recipe, ingredients));
        return new ShoppingListResponse(ingredients);
    }

    public void addIngredientsFromRecipe(Recipe recipe, Map<String, Integer> ingredients) {
        for (Map.Entry<Product, Integer> entry : recipe.getIngredients().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            Integer currentQuantity = ingredients.getOrDefault(product.getName(), 0);
            if(currentQuantity == 0) {
                ingredients.put(product.getName(), quantity);
            } else ingredients.replace(product.getName(), currentQuantity + quantity);
        }
    }
}
