package example.day11.todo.controller;

import example.day11.todo.dto.TodoDto;
import example.day11.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor    // final 멤버변수 생성자 제공
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired private TodoService todoService;
    // 1. 전체조회
    @GetMapping("")
    public ResponseEntity< ? > findAll(){
        List<TodoDto> result=todoService.findAll();
        return ResponseEntity.status(200).body(result); // HTTP 응답코드 200 또는 ok (성공)
    }
    // ResponseEntity : 응답객체, 사용목적 : 반환값 외 추가적인 자료 포함 <응답 코드>
    // < > : 제네릭, < ? > : 제네릭에 ? 타입 사용시 Object와 동일하게 모든 타입 대입 가능
        // ResponseEntity< Integer > : Integer타입만 반환
        // ResponseEntity< ? > : 모든 타입 반환

    // 2. 개별 조회
    @GetMapping("/detail")
    public ResponseEntity<?> findById(@RequestParam int id){
        TodoDto result=todoService.findById(id);
        return ResponseEntity.status(200).body(result);
    }

    // 3. title 개별 조회
    @GetMapping("/query1")
    public ResponseEntity<?> query1(@RequestParam String title){
        TodoDto result=todoService.query1(title);
        return ResponseEntity.ok(result);
    }

    // 4. title과 content 개별 조회
    @GetMapping("/query2")
    public ResponseEntity<?> query2(@RequestParam String title,
                                    @RequestParam String content){
        Map<String,Object> result=todoService.query2(title,content);
        return ResponseEntity.status(200).body(result);
    }

    // 5. title이 포함된 개별 조회
    @GetMapping("/query3")
    public ResponseEntity<?> query3(@RequestParam String title){
        List<TodoDto> result=todoService.query3(title);
        return ResponseEntity.status(200).body(result);
    }

    // 6. 페이징 처리
    @GetMapping("/page")
    public ResponseEntity<?> page(
            @RequestParam int page, // page는 조회할 현재페이지 번호
            @RequestParam int size){    // size는 페이지당 조회할 엔티티 개수
        return ResponseEntity.ok(todoService.page(page,size));
    }

    // 7. 페이징 처리 2
    @GetMapping("/page2")
    public ResponseEntity<?> page2(@RequestParam String keyword,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "3")int size){
                                        // defaultValue는 쿼리스트링 값이 존재하지 않으면 초기값
        return ResponseEntity.ok(todoService.page2(keyword,page,size));
    }
}
