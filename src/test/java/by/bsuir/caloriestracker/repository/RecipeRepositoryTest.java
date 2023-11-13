package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.Article;
import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.response.ArticleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EditorRepository editorRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void testManyToMany() {
        Recipe recipe = recipeRepository.save(Recipe.builder().likedUserList(new ArrayList<>()).build());
        User user = userRepository.save(User.builder().build());
        User foundUser = userRepository.findById(user.getId()).orElseThrow();
        foundUser.getFavouriteRecipes().add(recipe);
        recipe.getLikedUserList().add(user);
        recipeRepository.save(recipe);
        userRepository.save(foundUser);
        Recipe foundRecipe = recipeRepository.findById(recipe.getId()).orElseThrow();
        assertEquals(1, foundRecipe.getLikedUserList().size());
    }

    @Test
    void testOneToMany() {
        Editor editor = editorRepository.save(Editor.builder().articles(new ArrayList<>()).build());
        Article article = articleRepository.save(Article.builder().editor(editor).build());

        Editor foundEditor = editorRepository.findById(editor.getId()).orElseThrow();
        assertEquals(1, foundEditor.getArticles().size());
    }
}