package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
