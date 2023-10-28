package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.Article;
import by.bsuir.caloriestracker.models.AuthorizationData;
import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.repository.EditorRepository;
import by.bsuir.caloriestracker.request.EditorRequest;
import by.bsuir.caloriestracker.response.EditorResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
        return new EditorResponse(editorRepository.findAll());
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
    public void addRecipe(long editorId, Recipe recipe){
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
}
