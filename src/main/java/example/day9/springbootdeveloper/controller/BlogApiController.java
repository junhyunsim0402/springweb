package example.day9.springbootdeveloper.controller;

import example.day9.springbootdeveloper.domain.Article;
import example.day9.springbootdeveloper.dto.AddArticleRequest;
import example.day9.springbootdeveloper.dto.ArticleResponse;
import example.day9.springbootdeveloper.dto.UpdateArticleRequest;
import example.day9.springbootdeveloper.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogApiController {
    @Autowired private BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> adddArticle(@RequestBody AddArticleRequest request){
        Article saveArticle=blogService.save(request);
        // return ResponseEntity.status(HttpStatus.CREATED).body(saveArticle);
        return ResponseEntity.status(201).body(saveArticle);    // 200 : 조회/수정 성공   201 : 생성 성공
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles=blogService.findAll()
                .stream().map(ArticleResponse::new).toList();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long id){
        Article article=blogService.findById(id);
        return ResponseEntity.ok().body(new ArticleResponse(article));
    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        blogService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article article=blogService.update(id, request);
        return ResponseEntity.ok().body(article);
    }
}
