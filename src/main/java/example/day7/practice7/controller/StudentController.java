package example.day7.practice7.controller;

import example.day7.practice7.dto.StudentDto;
import example.day7.practice7.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired private StudentService studentService;
    @PostMapping("/student")
    public boolean StudentAdd(@RequestBody StudentDto studentDto){
        boolean result= studentService.StudentAdd(studentDto);
        return result;
    }
    @GetMapping("student")
    public List<StudentDto> findAll(){
        List<StudentDto> studentDtoList=studentService.findAll();
        return studentDtoList;
    }
}
