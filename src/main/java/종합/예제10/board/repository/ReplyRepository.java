package 종합.예제10.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import 종합.예제10.board.entity.ReplyEntity;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity,Integer> {
}
