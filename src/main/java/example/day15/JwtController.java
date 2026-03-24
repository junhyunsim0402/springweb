package example.day15;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor @RequestMapping("/api/jwt")
public class JwtController {
    private final JwtService jwtService;
    // [1] JWT 토큰 생성 == 데이터를 암호화 == 로그인정보 VS 세션
    @GetMapping("/create") public ResponseEntity<?> 토큰생성(@RequestParam String data){
        return ResponseEntity.ok(jwtService.토큰생성(data));
    }
    // [2] JWT 토큰
    @GetMapping("/read") public ResponseEntity<?> 값추출(@RequestParam String 토큰){
        return ResponseEntity.ok(jwtService.값추출(토큰));
    }
}
