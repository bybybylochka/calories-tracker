package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.dto.RecipeDto;
import by.bsuir.caloriestracker.dto.RecipeStatisticsDto;
import by.bsuir.caloriestracker.models.Kbju;
import by.bsuir.caloriestracker.models.Product;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.repository.RecipeRepository;
import by.bsuir.caloriestracker.request.RecipeRequest;
import by.bsuir.caloriestracker.response.RecipeResponse;
import by.bsuir.caloriestracker.response.RecipeStatisticsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final EditorService editorService;
    private final ProductService productService;

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe findById(long id) {
        return recipeRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Recipe with this id not found"));
    }
    public RecipeResponse findAll() {
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<RecipeDto> dtoList = allRecipes.stream().map(this::toDto).toList();
        return new RecipeResponse(dtoList);
    }

    public Recipe addRecipe(RecipeRequest request){
        Recipe recipe = buildRecipe(request);
        editorService.addRecipe(request.getEditorId(), recipe);
        return recipeRepository.save(recipe);
    }

    public RecipeResponse findRecipesByEditor() {
        List<Recipe> allByEditor = recipeRepository.findAllByEditor(editorService.getCurrentEditor());
        List<RecipeDto> dtoList = allByEditor.stream().map(this::toDto).toList();
        return new RecipeResponse(dtoList);
    }

    public RecipeStatisticsResponse getRecipeStatistics() {
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<RecipeStatisticsDto> statisticsDtoList = allRecipes.stream()
                .map(this::toStatisticsDto)
                .sorted(Comparator.comparing(RecipeStatisticsDto::getLikesQuantity, Comparator.reverseOrder()))
                .toList();
        return new RecipeStatisticsResponse(statisticsDtoList);
    }

    public Kbju getKbju(Recipe recipe) {
        int totalCalories = 0, totalCarbs = 0, totalProteins = 0, totalFats = 0;
        for(Map.Entry<Product, Integer> entry : recipe.getIngredients().entrySet()) {
            Product product = entry.getKey();
            totalCalories += product.getKbju().getCalories();
            totalCarbs += product.getKbju().getCarbohydrates();
            totalProteins += product.getKbju().getProteins();
            totalFats += product.getKbju().getFats();
        }
        return new Kbju(totalCalories, totalProteins, totalCarbs, totalFats);
    }

    private Recipe buildRecipe(RecipeRequest request){
        return Recipe.builder()
                .title(request.getTitle())
                .cookingTime(request.getCookingTime())
                .instruction(request.getInstruction())
                .publicationTime(LocalDateTime.now())
                .servingCount(request.getServingCount())
                .ingredients(mapIngredients(request))
                .editor(editorService.findById(request.getEditorId()))
                .build();
    }

    private RecipeDto toDto(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .publicationTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(recipe.getPublicationTime()))
                .cookingTime(recipe.getCookingTime())
                .editorName(recipe.getEditor().getFullName())
                .servingCount(recipe.getServingCount())
                .instruction(recipe.getInstruction())
                .ingredients(mapIngredients(recipe.getIngredients()))
                .kbju(getKbju(recipe))
                .build();
    }

    private RecipeStatisticsDto toStatisticsDto(Recipe recipe) {
        return RecipeStatisticsDto.builder()
                .id(recipe.getId())
                .recipeName(recipe.getTitle())
                .editorName(recipe.getEditor().getFullName())
                .likesQuantity(recipe.getLikedUserList().size())
                .build();
    }

    private Map<Product, Integer> mapIngredients(RecipeRequest request){
        return request.getIngredients().entrySet()
                .stream()
                .collect(Collectors.toMap(entry ->
                        productService.findById(entry.getKey()), Map.Entry::getValue));
    }

    private Map<String, Integer> mapIngredients(Map<Product, Integer> ingredients) {
        return ingredients.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue));
    }

}
