package pythonML1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FashionDto {
    private Integer id;
    private Integer age;
    private Integer gender;
    private Integer inflow;
    private Integer style;
    private Integer category;
}
