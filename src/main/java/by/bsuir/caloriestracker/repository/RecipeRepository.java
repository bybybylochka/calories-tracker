package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByEditor(Editor editor);
}
