package example.day7.연관관계;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data @Entity @Table(name = "reply")
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno;
    private String rcontent;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)  // FK 제약 조건 설정
    @JoinColumn(name = "bno")   // FK 필드명 설정
    private BoardEntity boardEntity;
}
/*
* - 영속성이란? 자바객체와 데이터베이스 데이터 간 매핑/연결 상태
* - cascade 속성이랑? PK와 FK 제약조건 옵션
*       CascadeType.ALL : 부모가 삭제/수정/저장 되면 자식도 같이 삭제/수정/저장 반영
*       CascadeType.REMOVE : 부모가 삭제되면 자식도 같이 삭제 반영
*       CascadeType.MERGE : 부모나 수정되면 자식도 같이 수정 반영
*       CascadeType.DETACH : 부모가 영속 해제되면 자식도 같이 영속 해제 된다
*       CascadeType.REFRESH : 부모가 재호출(갱신) 되면 자식도 같이 재호출(갱신)한다
*       CascadeType.REFRESH : 부모가 저장되면 자식도 같이 저장
*
* - fetch : PK를 조회시 FK 관계에서 엔티티 조회여부 선택
*   FetchType.EAGER : (기본값)해당 엔티티 조회시 참조 엔티티 모두 즉시 조회
*       - 특징 : 초기 로딩 느리다, 재사용시 빠르다, 불필요한 정보까지 있을경우 성능 저하
*   FetchType.LAZY : 해당 엔티티 조회시 참조 엔티티는 조회하지 않는다 < 참조 엔티티 호출시 조회 >
        - 특징 : 초기 로딩 빠르다, 재사용시 느리다. *필요한 정보까지만 적절하게 사용 * ( 지연 로딩 )

* - 단방향 / 양방향 활용
*   - 만약 1번 카테고리에 게시물 등록한다면, fk 필드에 fk값이 아닌 fk엔티티 대입한다
*       BoardEntity saveEntity=new BoardEntity();
        saveEntity.setCategory(1); [x]
        Category category=repository.findById(1).get(); [o]
        saveEntity.setCategory(category);
        repository.save(saveEntity);
*   - 만약 3번 게시물에 댓글 등록 한다면, fk필드에 fk값인 3대신에 3갖는 fk엔티티 찾아서 대입하자
*   ReplyEntity saveEntity = new ReplyEntity();
*       BoardEntity board=repository.findById(3).get();
*  saveEntity.setBoardEntity(board)
* repository.save(saveEntity)
* * */
