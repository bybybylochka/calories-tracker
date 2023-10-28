package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.Article;
import by.bsuir.caloriestracker.models.Product;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.repository.ProductRepository;
import by.bsuir.caloriestracker.repository.RecipeRepository;
import by.bsuir.caloriestracker.request.ArticleRequest;
import by.bsuir.caloriestracker.request.RecipeRequest;
import by.bsuir.caloriestracker.response.ProductResponse;
import by.bsuir.caloriestracker.response.RecipeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final EditorService editorService;
    private final ProductService productService;

    public Recipe findById(long id){
        return recipeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Recipe with this id not found"));
    }
    public RecipeResponse findAll(){
        return new RecipeResponse(recipeRepository.findAll());
    }

    public Recipe addRecipe(RecipeRequest request){
        Recipe recipe = buildRecipe(request);
        editorService.addRecipe(request.getEditorId(), recipe);
        return recipeRepository.save(recipe);
    }

    public Map<Product, Float> mapIgredients(RecipeRequest request){
        return request.getIngredients().entrySet()
                .stream()
                .collect(Collectors.toMap(entry ->
                        productService.findById(entry.getKey()), Map.Entry::getValue));
    }

    private Recipe buildRecipe(RecipeRequest request){
        return Recipe.builder()
                .title(request.getTitle())
                .cookingTime(request.getCookingTime())
                .instruction(request.getInstruction())
                .publicationTime(LocalDateTime.now())
                .servingCount(request.getServingCount())
                .ingredients(mapIgredients(request))
                .editor(editorService.findById(request.getEditorId()))
                .build();
    }
}
