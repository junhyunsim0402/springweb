package 종합.예제10.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import 종합.예제10.BaseTime;
import 종합.예제10.board.dto.ReplyDto;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @Builder
@Table(name = "reply")
public class ReplyEntity extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno;
    @Column(columnDefinition = "longtext not null")
    private String rcontent;
    @Column(length = 30,nullable = false)
    private String rwriter;
    @Column
    private Integer bno;

    public ReplyDto toDto(){
        return ReplyDto.builder()
                .rno(rno)
                .rcontent(rcontent)
                .rwriter(rwriter)
                .bno(bno)
                .create_date(getCreate_date()!=null ? getCreate_date().toString() : null)
                .update_date(getUpdate_date()!=null ? getUpdate_date().toString() : null)
                .build();
    }
}
