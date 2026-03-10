package example.day7.연관관계;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder @Table(name = "board") @Entity
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;

    private String bcontent;
    // 단방향 FK만들기
    @JoinColumn(name = "cno")  // 관례적으로 FK필드명도 PK필드명과 동일
    @ManyToOne  // 다수가 하나에게, M:1 관계 : 하나의 카테고리에 여러개게시물 참조
    private CategoryEntity categoryEntity;

    // ** 양방향 **
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude @Builder.Default
    private List<ReplyEntity> replyEntityList=new ArrayList<>();

}
/*
create table category (
        cno integer not null auto_increment,
        cname varchar(255),
        primary key (cno)
    ) engine=InnoDB
 */
