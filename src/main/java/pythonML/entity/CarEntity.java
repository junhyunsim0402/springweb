package pythonML.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pythonML.dto.CarDto;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @Builder @Table(name = "중고차매매데이터")
public class CarEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer 차량번호ID;

    @Column(nullable = false)
    private Double 평균연비;

    @Column(nullable = false)
    private Integer 누적주행거리키로;

    @Column(nullable = false)
    private Integer 출고후경과월수;

    @Column(nullable = false,columnDefinition = "int default 0")
    private Integer 사고감가건수;

    @Column(nullable = false,columnDefinition = "int default 0")
    private Integer 소유자변경횟수;

    @Column(nullable = false,columnDefinition = "int default 0")
    private Integer 매매가격만원;

    public CarDto ToDto(){
        return CarDto.builder()
                .차량번호ID(차량번호ID)
                .평균연비(평균연비)
                .누적주행거리키로(누적주행거리키로)
                .출고후경과월수(출고후경과월수)
                .사고감가건수(사고감가건수)
                .소유자변경횟수(소유자변경횟수)
                .매매가격만원(매매가격만원)
                .build();
    }
}
