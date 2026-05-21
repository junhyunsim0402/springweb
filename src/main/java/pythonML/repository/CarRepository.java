package pythonML.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pythonML.entity.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Integer> {
}
