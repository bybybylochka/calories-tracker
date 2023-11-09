package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.models.Admin;
import by.bsuir.caloriestracker.models.Article;
import by.bsuir.caloriestracker.repository.AdminRepository;
import by.bsuir.caloriestracker.repository.ArticleRepository;
import by.bsuir.caloriestracker.repository.EditorRepository;
import by.bsuir.caloriestracker.request.ArticleRequest;
import by.bsuir.caloriestracker.response.AdminResponse;
import by.bsuir.caloriestracker.response.ArticleResponse;
import by.bsuir.caloriestracker.response.RecipeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final EditorService editorService;

    public Article findById(long id){
        return articleRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Article with this id not found"));
    }
    public ArticleResponse findAll(){
        return new ArticleResponse(articleRepository.findAll());
    }

    public Article addArticle(ArticleRequest request){
        Article article = buildArticle(request);
        editorService.addArticle(request.getEditorId(), article);
        return articleRepository.save(article);
    }

    private Article buildArticle(ArticleRequest request){
        return Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .publicationTime(LocalDateTime.now())
                .editor(editorService.findById(request.getEditorId()))
                .build();
    }
    public ArticleResponse findArticlesByEditor(){
        return new ArticleResponse(articleRepository.findAllByEditor(editorService.getCurrentEditor()));
    }
}
