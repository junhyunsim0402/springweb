package example.day7.practice7.service;

import example.day7.practice7.dto.EnrollDto;
import example.day7.practice7.entity.CourseEntity;
import example.day7.practice7.entity.EnrollEntity;
import example.day7.practice7.entity.StudentEntity;
import example.day7.practice7.repository.CourseRepository;
import example.day7.practice7.repository.EnrollRepository;
import example.day7.practice7.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollService {
    @Autowired private EnrollRepository enrollRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private CourseRepository courseRepository;

    public boolean EnrollAdd(EnrollDto enrollDto) {
        StudentEntity student = studentRepository.findById(enrollDto.getStudentId()).orElse(null);
        CourseEntity course = courseRepository.findById(enrollDto.getCourseId()).orElse(null);
        if (student == null || course == null) return false;

        EnrollEntity saved = enrollRepository.save(enrollDto.toEntity(student, course));
        return saved.getEnrollId() >= 1;
    }

    public List<EnrollDto> findAll() {
        List<EnrollDto> dtoList = new ArrayList<>();
        enrollRepository.findAll().forEach(entity -> dtoList.add(entity.toDto()));
        return dtoList;
    }
}
