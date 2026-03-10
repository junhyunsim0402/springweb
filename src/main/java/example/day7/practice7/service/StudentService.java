package example.day7.practice7.service;

import example.day7.practice7.dto.StudentDto;
import example.day7.practice7.entity.StudentEntity;
import example.day7.practice7.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired private StudentRepository studentRepository;
    public boolean StudentAdd(StudentDto studentDto){
        StudentEntity saved=studentRepository.save(studentDto.toEntity());
        if(saved.getStudentId()>=1)return true;
        return false;
    }
    public List<StudentDto> findAll(){
        List<StudentEntity> studentEntityList=studentRepository.findAll();
        List<StudentDto> studentDtoList=new ArrayList<>();
        studentEntityList.forEach(entity->{
            studentDtoList.add(entity.toDto());
        });
        return studentDtoList;
    }
}
