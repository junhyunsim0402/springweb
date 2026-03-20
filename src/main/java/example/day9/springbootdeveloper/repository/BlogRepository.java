package example.day9.springbootdeveloper.repository;

import example.day9.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository
        extends JpaRepository<Article,Long> {
}
