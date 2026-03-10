package 종합.예제10.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import 종합.예제10.board.entity.ReplyEntity;

@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class ReplyDto {
    private Integer rno;            // 댓글번호
    private String rcontent;    // 댓글내용
    private String rwriter;     // 댓글작성자
    private String rdate;       // 댓글작성일
    private Integer bno;            // 게시물번호 (참조관계)
    private String update_date;
    private String create_date;
    public ReplyEntity toEntity(){
        return ReplyEntity.builder()
                .rno(rno)
                .rcontent(rcontent)
                .rwriter(rwriter)
                .bno(bno)
                .build();
    }
}
