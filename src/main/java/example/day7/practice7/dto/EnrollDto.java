package example.day7.practice7.dto;

import example.day7.practice7.entity.CourseEntity;
import example.day7.practice7.entity.EnrollEntity;
import example.day7.practice7.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollDto {
    private Integer enrollId;
    private String status;
    private Integer studentId;
    private String studentName;
    private Integer courseId;
    private String courseName;
    private String create_date;
    private String update_date;

    public EnrollEntity toEntity(StudentEntity student, CourseEntity course) {
        return EnrollEntity.builder()
                .enrollId(enrollId)
                .status(status)
                .student(student)
                .course(course)
                .build();
    }
}