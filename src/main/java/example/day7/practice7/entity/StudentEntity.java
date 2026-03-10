package example.day7.practice7.entity;

import example.day7.practice7.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder @Table(name = "student")
public class StudentEntity extends BaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;

    private String studentName;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude @Builder.Default
    private List<EnrollEntity> entityList=new ArrayList<>();

    public StudentDto toDto(){
        return StudentDto.builder()
                .studentId(studentId)
                .studentName(studentName)
                .create_date(getCreate_date().toString())
                .update_date(getUpdate_date().toString())
                .build();
    }
}
