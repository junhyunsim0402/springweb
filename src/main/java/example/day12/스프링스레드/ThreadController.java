package example.day12.스프링스레드;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequiredArgsConstructor @RequestMapping("/api/thread")
public class ThreadController {
    @Autowired final ThreadService threadService;
    // WAS 스레드풀 사용하여 여러개의 스레드를 관리한다.
    // 매핑/요청/servlet 1개당 스레드 1개 할당 <스레드풀>
    // [1] 사용자로부터 (동기화)요청이 들어오면 서비스에게 요청/응답
    // ** 요청이 들어오고 서비스가 응답하기까지 컨트롤러 기다린다.
    @GetMapping("/test1") public ResponseEntity<?> test1(){
        System.out.println("ThreadController.test1");
        return ResponseEntity.ok(threadService.test1());
    }

    // [2] (비동기화) 요청이 들어오고 서비스가 응답하기 전에 컨트롤러는 먼저 응답한다.
    @GetMapping("/test2") public ResponseEntity<?> test2(){
        System.out.println("ThreadController.test2");
        threadService.test2();
        return ResponseEntity.ok(true);
    }
}
