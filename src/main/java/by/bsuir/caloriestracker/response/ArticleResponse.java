package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.models.Article;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ArticleResponse {
    private List<Article> articles;
}
