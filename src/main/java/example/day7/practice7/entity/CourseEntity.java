package example.day7.practice7.entity;

import example.day7.practice7.dto.CourseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @Builder @Table(name = "course")
public class CourseEntity extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;
    @OneToMany(mappedBy = "course") @ToString.Exclude @Builder.Default
    private List<EnrollEntity> entityList=new ArrayList<>();
    public CourseDto toDto(){
        return CourseDto.builder()
                .courseId(courseId)
                .courseName(courseName)
                .create_date(getCreate_date().toString())
                .update_date(getUpdate_date().toString())
                .build();
    }
}
