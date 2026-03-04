package example.종합.예제9.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private Integer bno;    // int -> Integer 사용하여 null 값 대응
    private String bcontent;
    private String bwriter;
    private String bdate;
}