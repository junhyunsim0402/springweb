package example.springbootdeveloper.service;

import example.springbootdeveloper.domain.Article;
import example.springbootdeveloper.dto.AddArticleRequest;
import example.springbootdeveloper.dto.ArticleResponse;
import example.springbootdeveloper.dto.UpdateArticleRequest;
import example.springbootdeveloper.repository.BlogRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired private BlogRepository blogRepository;
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }
    public List<Article> findAll(){
        return blogRepository.findAll();
    }
    public Article findById(Long id){
        return blogRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("아이디를 찾을수 없습니다"+id));
    }
    public void delete(Long id){
        blogRepository.deleteById(id);
    }
    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article=blogRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("아이디를 찾을수 없음"+id));
        article.setTitle(request.getTitle());article.setContent(request.getContent());
        return article;
    }
}
