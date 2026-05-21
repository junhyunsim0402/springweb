package pythonML.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pythonML.dto.CarDto;
import pythonML.service.CarService;

@RestController @RequestMapping("/api") @RequiredArgsConstructor
public class CarController {
    @Autowired private final CarService carService;
    // [1] 학습요청
    @GetMapping("/admin")
    public ResponseEntity<?> 학습요청( ){
        return ResponseEntity.ok( carService.학습요청() );
    }
    // [2] 예측요청
    @PostMapping("/user")
    public ResponseEntity<?> 예측요청(@RequestBody CarDto carDto){
        return ResponseEntity.ok( carService.예측요청( carDto ) );
    }
}
