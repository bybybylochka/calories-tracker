package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.Product;
import by.bsuir.caloriestracker.models.Recipe;
import by.bsuir.caloriestracker.models.User;
import by.bsuir.caloriestracker.repository.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class RecipeServiceTest {
    @InjectMocks
    private RecipeService recipeService;
    @Mock
    private RecipeRepository recipeRepository;

    @Test
    void testFindByIdWhenRecipeWithGivenIdExists() {
        Recipe recipe = Recipe.builder().build();
        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        assertDoesNotThrow(() -> recipeService.findById(1L));
        Mockito.verify(recipeRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdWhenRecipeWithGivenIdDoesNotExist() {
        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrowsExactly(IllegalArgumentException.class, () -> recipeService.findById(1L));
    }
}