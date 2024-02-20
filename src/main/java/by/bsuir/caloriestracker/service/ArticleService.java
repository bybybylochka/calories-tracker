package by.bsuir.caloriestracker.service;

import by.bsuir.caloriestracker.dto.ArticleDto;
import by.bsuir.caloriestracker.models.Article;
import by.bsuir.caloriestracker.repository.ArticleRepository;
import by.bsuir.caloriestracker.request.ArticleRequest;
import by.bsuir.caloriestracker.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        List<Article> articleList = articleRepository.findAll();
        return new ArticleResponse(articleList.stream().map(this::toDto).toList());
    }

    public ArticleDto addArticle(ArticleRequest request){
        Article article = buildArticle(request);
        editorService.addArticle(editorService.getCurrentEditor(), article);
        return toDto(articleRepository.save(article));
    }

    public ArticleResponse findArticlesByEditor(){
        List<Article> articleList = articleRepository.findAllByEditor(editorService.getCurrentEditor());
        return new ArticleResponse(articleList.stream().map(this::toDto).toList());
    }

    private Article buildArticle(ArticleRequest request){
        return Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .publicationTime(LocalDateTime.now())
                .editor(editorService.getCurrentEditor())
                .build();
    }

    private ArticleDto toDto(Article article) {
        return ArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .publicationTime(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(article.getPublicationTime()))
                .editorName(article.getEditor().getFullName())
                .build();
    }
}
