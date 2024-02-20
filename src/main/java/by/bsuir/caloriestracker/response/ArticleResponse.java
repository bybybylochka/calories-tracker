package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.dto.ArticleDto;
import by.bsuir.caloriestracker.models.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ArticleResponse {
    private List<ArticleDto> articles;
}
