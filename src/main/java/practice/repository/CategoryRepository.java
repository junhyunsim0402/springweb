package practice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.entity.CategoryEntiy;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntiy,Integer> {
}
