package example.day6.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
@Builder
public class GoodsDto {
    private Integer gno;    // 제품번호
    private String gname;   // 제품명
    private Integer gprice; // 제품가격
    private String gdesc;   // 제품설명
    // + baseTime
    private String createDate;
    private String updateDate;

    // DTO --> ENTITY 변환 함수
    public GoodsEntity toEntity(){
        // 빌더패턴이란?new 생성자가 아닌 함수로 객체로 생성하는 방법
        // this란? 해당 메소드/함수 실행한 객체
        return GoodsEntity.builder()    // 빌더시작
                .gno(this.gno)
                .gname(this.gname)
                .gprice(this.gprice)
                .gdesc(this.gdesc)
                .build(); // 빌더 끝
    }
}
