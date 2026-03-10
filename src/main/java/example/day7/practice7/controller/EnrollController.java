package example.day7.practice7.controller;

import example.day7.practice7.dto.EnrollDto;
import example.day7.practice7.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnrollController {
    @Autowired
    private EnrollService enrollService;

    @PostMapping("/enroll")
    public boolean EnrollAdd(@RequestBody EnrollDto enrollDto) {
        return enrollService.EnrollAdd(enrollDto);
    }

    @GetMapping("/enroll")
    public List<EnrollDto> findAll() {
        return enrollService.findAll();
    }
}