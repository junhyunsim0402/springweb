package example.springbootdeveloper.dto;

import example.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ArticleListViewResponse {
    private Long id;
    private String title;
    private String content;
    public ArticleListViewResponse(Article article){
        this.id=article.getId();
        this.title=article.getTitle();
        this.content=article.getContent();
    }
}
