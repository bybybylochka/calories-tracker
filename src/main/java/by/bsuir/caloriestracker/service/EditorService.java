package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.dto.ConsumedProductDto;
import by.bsuir.caloriestracker.dto.EditorDto;
import by.bsuir.caloriestracker.models.*;
import by.bsuir.caloriestracker.repository.EditorRepository;
import by.bsuir.caloriestracker.request.EditorRequest;
import by.bsuir.caloriestracker.response.ConsumedProductResponse;
import by.bsuir.caloriestracker.response.EditorResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class EditorService {
    private final EditorRepository editorRepository;
    private final AuthorizationDataService authorizationDataService;

    public Editor findById(long id){
        return editorRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Editor with this id not found"));
    }
    public EditorResponse findAll(){
        List<Editor> editorList = editorRepository.findAll();
        List<EditorDto> dtoList = editorList.stream().map(this::toDto).toList();
        return new EditorResponse(dtoList);
    }

    public Editor findByUsername(String username) {
        return editorRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Editor with such username not found"));
    }

    public Editor addEditor(EditorRequest request){
        AuthorizationData authorizationData = authorizationDataService
                .addAuthorizationData(request.getAuthenticationData());
        Editor editor = buildEditor(request, authorizationData);
        return editorRepository.save(editor);
    }
    public void addArticle(long editorId, Article article){
        Editor editor = findById(editorId);
        List<Article> articles = editor.getArticles();
        articles.add(article);
        editor.setArticles(articles);
        editorRepository.save(editor);
    }

    public void addRecipe (long editorId, Recipe recipe) {
        Editor editor = findById(editorId);
        List<Recipe> recipes = editor.getRecipes();
        recipes.add(recipe);
        editor.setRecipes(recipes);
        editorRepository.save(editor);
    }

    private Editor buildEditor(EditorRequest request, AuthorizationData authorizationData){
        return Editor.builder()
                .fullName(request.getFullName())
                .authorizationData(authorizationData)
                .recipes(new ArrayList<>())
                .articles(new ArrayList<>())
                .build();
    }
    public Editor getCurrentEditor() {
        Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) currentAuthentication.getPrincipal()).getUsername();
        return findByUsername(username);
    }

    private int getTotalRecipeLikesCount(Editor editor) {
        return editor.getRecipes().stream()
                .mapToInt(recipe -> recipe.getLikedUserList().size())
                .reduce(0, Integer::sum);
    }

    private double getWeekActivity(Editor editor) {
        int totalPostCount = editor.getRecipes().size() + editor.getArticles().size();
        LocalDate registrationDate = editor.getAuthorizationData().getCreatedAt();
        int daysSinceRegistration = Period.between(registrationDate, LocalDate.now()).getDays();
        return ((double) daysSinceRegistration / 7) / totalPostCount;
    }

    private EditorDto toDto(Editor editor) {
        return EditorDto.builder()
                .id(editor.getId())
                .fullName(editor.getFullName())
                .recipesCount(editor.getRecipes().size())
                .articlesCount(editor.getArticles().size())
                .totalRecipesLikesCount(getTotalRecipeLikesCount(editor))
                .weekActivity(getWeekActivity(editor))
                .build();
    }
}
