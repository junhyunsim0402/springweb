package example.day2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller // HTTP 기능 + 빈 등록
public class RestController2 {
    // 100을 반환하는 메소드
    @GetMapping("/example/day2/task")   // was(톰캣)주소/내가정의한주소, localhost::8080/day2/task
    @ResponseBody   //Response(응답) Body(객체 지향): 응답 자료 자동 타입 변환, java(객체지향) <--번역--> HTTP(문자)
    // 즉] JAVA타입을 자동으로 HTTP contents Type 변환해준다. int -> application/json
    public int method1(){
        System.out.println("RestController2.method1"); return 100;
    }
    // 2. 문자열 반환 하는 메소드
    @GetMapping("/example/day2/task2")
    @ResponseBody   // java String -> text/plain
    public String method2(){
        System.out.println("RestController2.method2");return "유재석";
    }
    // 3. Map 타입 반환하는 메소드
    @GetMapping("/example/day2/task3")
    @ResponseBody
    public Map<String,Object> method3(){
        Map<String,Object> map=new HashMap<>();
        map.put("유재석",100); map.put("강호동",90);
        return map;
    }
    // 4. boolean 타입 반환 하는 메소드
    @GetMapping("/example/day2/task4") @ResponseBody    // java boolean -> application/json
    public boolean method4(){
        return true;
    }
    // 5. DTO 타입 반환 하는 메소드
    @GetMapping("/example/day2/task5") @ResponseBody
    public TaskDto method5(){
        TaskDto taskDto=new TaskDto();
        taskDto.name="유재석";taskDto.point=100;
        return taskDto;
    }
    // 즉] String 제외한 자바의 대부분 타입은 application/json 으로 HTTP Content-> Type으로 설정된다.
}
class TaskDto{ String name; int point;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getPoint() { return point; }
    public void setPoint(int point) { this.point = point; }
}