package example.day7.practice7.service;

import example.day7.practice7.dto.CourseDto;
import example.day7.practice7.entity.CourseEntity;
import example.day7.practice7.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired private CourseRepository courseRepository;
    public boolean CourseAdd(CourseDto courseDto){
        CourseEntity courseEntity=courseRepository.save(courseDto.toEntity());
        if(courseEntity.getCourseId()>=1)return true;
        return false;
    }
    public List<CourseDto> findAll(){
        List<CourseEntity> courseEntityList=courseRepository.findAll();
        List<CourseDto> courseDtoList=new ArrayList<>();
        courseEntityList.forEach(entity->{
            courseDtoList.add(entity.toDto());
        });
        return courseDtoList;
    }
}
