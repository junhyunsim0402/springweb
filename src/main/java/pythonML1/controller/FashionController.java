package pythonML1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pythonML1.dto.FashionRequestDto;
import pythonML1.service.FashionService;

@RestController @RequestMapping("/api") @RequiredArgsConstructor
public class FashionController {
    @Autowired private FashionService fashionService;
    @GetMapping("/admin")
    public ResponseEntity<?> admin(){
        return ResponseEntity.ok(fashionService.admin());
    }
    @PostMapping("/user")
    public ResponseEntity<?> user(@RequestBody FashionRequestDto fashionRequestDto){
        return ResponseEntity.ok(fashionService.user(fashionRequestDto));
    }
}
