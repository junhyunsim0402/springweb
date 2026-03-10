package example.day7.practice7.entity;

import example.day7.practice7.dto.EnrollDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder @Table(name = "enroll") @Entity
public class EnrollEntity extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollId;

    private String status;

    @ManyToOne @JoinColumn(name = "student_id")
    private StudentEntity student;

    @ManyToOne @JoinColumn(name = "course_id")
    private CourseEntity course;
    public EnrollDto toDto() {
        return EnrollDto.builder()
                .enrollId(enrollId)
                .status(status)
                .studentId(student.getStudentId())
                .studentName(student.getStudentName())
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .create_date(getCreate_date().toString())
                .update_date(getUpdate_date().toString())
                .build();
    }
}
