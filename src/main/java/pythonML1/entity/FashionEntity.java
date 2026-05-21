package pythonML1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pythonML1.dto.FashionDto;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "fashion")
public class FashionEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private Integer gender;
    @Column(nullable = false)
    private Integer inflow;
    @Column(nullable = false,columnDefinition = "int default 0")
    private Integer style;
    @Column(nullable = false)
    private Integer category;
    public FashionDto ToDto(){
        return FashionDto.builder()
                .age(age)
                .gender(gender)
                .inflow(inflow)
                .style(style)
                .category(category)
                .build();
    }
}
