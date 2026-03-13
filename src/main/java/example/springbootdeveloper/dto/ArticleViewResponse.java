package example.springbootdeveloper.dto;

import example.springbootdeveloper.domain.Article;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
public class ArticleViewResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;

    public ArticleViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createAt=article.getCreateAt();
    }
}
