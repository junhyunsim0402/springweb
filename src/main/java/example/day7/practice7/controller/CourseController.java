package example.day7.practice7.controller;

import example.day7.practice7.dto.CourseDto;
import example.day7.practice7.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired private CourseService courseService;
    @PostMapping("/course")
    public boolean CourseAdd(@RequestBody CourseDto courseDto){
        boolean result= courseService.CourseAdd(courseDto);
        return result;
    }
    @GetMapping("/course")
    public List<CourseDto> findAll(){
        List<CourseDto> result=courseService.findAll();
        return result;
    }
}
