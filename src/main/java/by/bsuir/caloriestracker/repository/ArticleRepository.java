package by.bsuir.caloriestracker.repository;

import by.bsuir.caloriestracker.models.Article;
import by.bsuir.caloriestracker.models.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByEditor(Editor editor);
}
