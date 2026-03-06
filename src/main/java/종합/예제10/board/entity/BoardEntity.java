package 종합.예제10.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import 종합.예제10.BaseTime;
import 종합.예제10.board.dto.BoardDto;

@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Data
@Table(name = "board")
public class BoardEntity extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;

    @Column(nullable = false, length = 255)   // not null
    private String btitle;

    @Column(columnDefinition = "longtext not null") // 직접 SQL설정
    private String bcontent;

    @Column(length = 30,nullable = false)   // not null
    private String bwriter;
    // + 생성/수정 날짜/시간은 BaseTime으로 상속

    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(bno)
                .btitle(btitle)
                .bcontent(bcontent)
                .bwriter(bwriter)
                .create_date(getCreate_date()!=null ? getCreate_date().toString() : null)
                .update_date(getUpdate_date()!=null ? getUpdate_date().toString() : null)
                .build();
    }
}
