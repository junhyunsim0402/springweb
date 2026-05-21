package pythonML1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pythonML1.entity.FashionEntity;

@Repository
public interface FashionRepository extends JpaRepository<FashionEntity,Integer> {
}
