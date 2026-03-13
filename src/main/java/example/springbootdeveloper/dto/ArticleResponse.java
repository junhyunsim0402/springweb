package example.springbootdeveloper.dto;

import example.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ArticleResponse {
    private String title;
    private String content;
    public ArticleResponse(Article article){
        this.title=article.getTitle();
        this.content=article.getContent();
    }
}
