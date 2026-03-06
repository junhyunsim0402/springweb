package 종합.예제10.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import 종합.예제10.board.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
}
