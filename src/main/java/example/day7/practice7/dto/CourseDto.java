package example.day7.practice7.dto;

import example.day7.practice7.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private String create_date;
    private String update_date;
    public CourseEntity toEntity(){
        return CourseEntity.builder()
                .courseId(courseId)
                .courseName(courseName)
                .build();
    }
}