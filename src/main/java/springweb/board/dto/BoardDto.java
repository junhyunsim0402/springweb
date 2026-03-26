package springweb.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import springweb.board.entity.BoardEntity;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class BoardDto {
    private Long bno;   // 게시물 번호
    private String btitle;  // 게시물 제목
    private String bcontent;    // 게시물 내용
    private String bfile;   // 게시물 첨부 파일 ( DB용도 )
    // + Dto에는 엔티티 정보를 포함하지 않고 필요한 정보만 맴버변수 구성
    private Long mno;
    private String nickname;
    private String createDate;
    private String updateDate;
    // + 첨부파일 매핑, 여러개 이면 List< MultipartFile >
    private MultipartFile uploadFile;   // ( 업로드 용도 )

    // + toEntity
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .btitle(btitle)
                .bcontent(bcontent)
                .bfile(bfile)
                // .memberEntity() fk는 서비스에서 대입
                .build();
    }
}
