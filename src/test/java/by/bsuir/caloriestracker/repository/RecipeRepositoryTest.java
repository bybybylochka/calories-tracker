package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.Article;
import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.response.ArticleResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private EditorRepository editorRepository;

    @Test
    @Rollback
    void findAllByEditorWhenEditorIsPresent() {
        Editor editor = Editor.builder().build();
        Editor anotherEditor = Editor.builder().build();
        editorRepository.saveAll(List.of(editor, anotherEditor));
        Recipe firstRecipe = Recipe.builder().editor(editor).build();
        Recipe secondRecipe = Recipe.builder().editor(anotherEditor).build();
        recipeRepository.saveAll(List.of(firstRecipe, secondRecipe));

        List<Recipe> allByEditor = recipeRepository.findAllByEditor(editor);

        assertEquals(1, allByEditor.size());
        assertEquals(firstRecipe, allByEditor.get(0));
    }

    @Test
    @Rollback
    void findAllByEditorWhenEditorIsNotPresent() {
        Editor editor = Editor.builder().build();
        Editor anotherEditor = Editor.builder().build();
        editorRepository.saveAll(List.of(editor, anotherEditor));
        Recipe firstRecipe = Recipe.builder().editor(anotherEditor).build();
        Recipe secondRecipe = Recipe.builder().editor(anotherEditor).build();
        recipeRepository.saveAll(List.of(firstRecipe, secondRecipe));

        List<Recipe> allByEditor = recipeRepository.findAllByEditor(editor);

        assertTrue(allByEditor.isEmpty());
    }

    @Test
    @Rollback
    void testFindAllTitleContainingIgnoreCaseWhenInputMatches() {
        final String input = "Rec";
        Recipe firstRecipe = Recipe.builder().title("recipe").build();
        Recipe secondRecipe = Recipe.builder().title("Rectangle").build();
        recipeRepository.saveAll(List.of(firstRecipe, secondRecipe));

        List<Recipe> allContaining = recipeRepository.findByTitleContainingIgnoreCase(input);

        assertEquals(2, allContaining.size());
    }

    @Test
    @Rollback
    void testFindAllTitleContainingIgnoreCaseWhenInputDoesNotMatches() {
        final String input = "no match";
        Recipe firstRecipe = Recipe.builder().title("recipe").build();
        Recipe secondRecipe = Recipe.builder().title("Rectangle").build();
        recipeRepository.saveAll(List.of(firstRecipe, secondRecipe));

        List<Recipe> allContaining = recipeRepository.findByTitleContainingIgnoreCase(input);

        assertTrue(allContaining.isEmpty());
    }

}