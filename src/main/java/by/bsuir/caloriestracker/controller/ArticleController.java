package by.bsuir.caloriestracker.controller;


import by.bsuir.caloriestracker.dto.ArticleDto;
import by.bsuir.caloriestracker.models.Article;
import by.bsuir.caloriestracker.request.ArticleRequest;
import by.bsuir.caloriestracker.response.ArticleResponse;
import by.bsuir.caloriestracker.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_EDITOR')")
    public ArticleDto addArticle(@RequestBody ArticleRequest request){
        return articleService.addArticle(request);
    }

    @GetMapping()
    public ArticleResponse getAllArticles(){
        return articleService.findAll();
    }
    @GetMapping("/get/author")
    public ArticleResponse getArticlesByEditor(){
        return articleService.findArticlesByEditor();
    }
}
