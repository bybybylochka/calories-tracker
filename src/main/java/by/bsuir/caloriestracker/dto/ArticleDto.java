package by.bsuir.caloriestracker.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ArticleDto {
    private long id;
    private String title;
    private String content;
    private String publicationTime;
    private String editorName;
}
