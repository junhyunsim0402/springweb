package example.day7.practice7.dto;

import example.day7.practice7.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private Integer studentId;
    private String studentName;
    private String create_date;
    private String update_date;

    public StudentEntity toEntity(){
        return StudentEntity.builder()
                .studentId(studentId)
                .studentName(studentName)
                .build();
    }
}