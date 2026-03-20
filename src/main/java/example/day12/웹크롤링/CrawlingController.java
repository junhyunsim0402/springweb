package example.day12.웹크롤링;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor @RequestMapping("/api/craw")
public class CrawlingController {
    private final CrawlingService crawlingService;
    @GetMapping("/test1") public ResponseEntity<?> test1(){
        return ResponseEntity.ok(crawlingService.test1());
    }
    @GetMapping("/test2") public ResponseEntity<?> test2(){
        return ResponseEntity.ok(crawlingService.test1());
    }
    @GetMapping("/test3") public ResponseEntity<?> test3(){
        return ResponseEntity.ok(crawlingService.test3());
    }
    @GetMapping("/test4") public ResponseEntity<?> test4(){
        return ResponseEntity.ok(crawlingService.test4());
    }
}
