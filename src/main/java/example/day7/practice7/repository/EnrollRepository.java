package example.day7.practice7.repository;

import example.day7.practice7.entity.EnrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollRepository extends JpaRepository<EnrollEntity,Integer> {
}
