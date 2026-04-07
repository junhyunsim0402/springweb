package springweb.board.entity;

import jakarta.persistence.*;
import lombok.*;
import springweb.board.dto.BoardDto;
import springweb.member.entity.BaseTime;
import springweb.member.entity.MemberEntity;

@Entity @Table(name = "board") @NoArgsConstructor @AllArgsConstructor @Data @Builder
public class BoardEntity extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;   // 게시물 번호
    @Column(nullable = false)
    private String btitle;  // 게시물 제목
    @Column(nullable = false,columnDefinition = "longtext")
    private String bcontent;    // 게시물 내용
    @Column(length = 255) // 주로 첨부파일은 파일자체를 저장하는게 아니라, 파일의위치(서버내 경로) 저장
    private String bfile;   // 게시물 첨부 파일

    // + 단방향 : 한명의 회원이 여러개 게시물 작성 1:다 관계
    @ManyToOne @JoinColumn(name = "mno") @ToString.Exclude
    private MemberEntity memberEntity;

    // + toDto
    public BoardDto toDto(){
        return BoardDto.builder()
                .btitle(btitle)
                .bcontent(bcontent)
                .bfile(bfile)
                .mno(memberEntity.getMno()) // 작성자FK의 회원번호
                .nickname(memberEntity.getNickname())   // 작성자FK의 닉네임
                .createDate(getCreateDate().toString()) // 작성일
                .build();
    }
}
