package 종합.예제10.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import 종합.예제10.board.entity.BoardEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private Integer bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private String create_date;
    private String update_date;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bno(bno)
                .btitle(btitle)
                .bcontent(bcontent)
                .bwriter(bwriter)
                .build();
    }
}
