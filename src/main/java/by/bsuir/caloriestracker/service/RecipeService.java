package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.dto.ProductDto;
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
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final EditorService editorService;
    private final ProductService productService;

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
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

    public RecipeDto addRecipe(RecipeRequest request){
        Recipe recipe = buildRecipe(request);
        editorService.addRecipe(editorService.getCurrentEditor().getId(), recipe);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return toDto(savedRecipe);
    }

    public RecipeResponse findRecipesByEditor() {
        List<Recipe> allByEditor = recipeRepository.findAllByEditor(editorService.getCurrentEditor());
        List<RecipeDto> dtoList = allByEditor.stream().map(this::toDto).toList();
        return new RecipeResponse(dtoList);
    }

    public RecipeResponse findListByTitle(String title, int maxCalories, boolean shouldSort){
        List<Recipe> recipes;
        if(title.equals("")){
            recipes=recipeRepository.findAll();
        }
        else{
            recipes=recipeRepository.findByTitleContainingIgnoreCase(title);
        }
        Stream<RecipeDto> filteredRecipeStream = recipes
                .stream()
                .map(this::toDto)
                .filter(recipe->recipe.getKbju().getCalories()<=maxCalories);
        List<RecipeDto> result = shouldSort
                ? filteredRecipeStream
                    .sorted(Comparator.comparing(RecipeDto::getLikesCount, Comparator.reverseOrder()))
                    .toList()
                : filteredRecipeStream.toList();
        return new RecipeResponse(result);
    }

    public RecipeResponse sortRecipesByLikesQuantity(){
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<RecipeDto> sortedRecipes = allRecipes.stream()
                .sorted(Comparator.comparing(recipe->recipe.getLikedUserList().size(), Comparator.reverseOrder()))
                .map(this::toDto)
                .toList();
        return new RecipeResponse(sortedRecipes);
    }



    public RecipeResponse filterRecipesByCalories(int maxCaloriesQuantity){
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<RecipeDto> filteredRecipes = allRecipes.stream()
                .map(this::toDto)
                .filter(recipe->recipe.getKbju().getCalories()<=maxCaloriesQuantity)
                .toList();
        return new RecipeResponse(filteredRecipes);
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
        int totalCalories = 0, totalCarbs = 0, totalProteins = 0, totalFats = 0, totalWeight=0;
        for(Map.Entry<Product, Integer> entry : recipe.getIngredients().entrySet()) {
            Product product = entry.getKey();
            totalWeight += entry.getValue();
            totalCalories += product.getKbju().getCalories()*entry.getValue()/100;
            totalCarbs += product.getKbju().getCarbohydrates()*entry.getValue()/100;
            totalProteins += product.getKbju().getProteins()*entry.getValue()/100;
            totalFats += product.getKbju().getFats()*entry.getValue()/100;

        }
        totalCalories = totalCalories*100/totalWeight;
        totalCarbs = totalCarbs*100/totalWeight;
        totalProteins = totalProteins*100/totalWeight;
        totalFats = totalFats*100/totalWeight;
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
                .editor(editorService.findById(editorService.getCurrentEditor().getId()))
                .build();
    }

    public RecipeDto toDto(Recipe recipe) {

        return RecipeDto.builder()
                .id(recipe.getId())
                .title(recipe.getTitle())
                .publicationTime(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(recipe.getPublicationTime()))
                .cookingTime(recipe.getCookingTime())
                .likesCount(recipe.getLikedUserList().size())
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
