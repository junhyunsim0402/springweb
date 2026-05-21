package pythonML.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pythonML.entity.CarEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private Integer 차량번호ID;
    private Double 평균연비;
    private Integer 누적주행거리키로;
    private Integer 출고후경과월수;
    private Integer 사고감가건수;
    private Integer 소유자변경횟수;
    private Integer 매매가격만원;
}
