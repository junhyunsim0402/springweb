package day2.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

// @Component  // 빈 등록
// @Controller // + HTTP 통신 기능 => 사용처 : View(화면) 반환
@RestController // + ResponseBody 포함    => 사용처: 값(JSON) 반환
@RequestMapping("/day2")// @RequestMapping("/공통URL")   해당 컨트롤러내 메소드들이 사용하는 공통 URL정의
public class RestController3 {
    // 1. 클래스가 @RestController이므로 @ResponseBody 생략해도 된다
    @GetMapping("/task6")   // 클래스가 @RequestMapping("공통URL")을 가지므로 localhost:8080/day2/task6
    public String method1(){ return "서버에게 받은 메세지"; }

    // 2. http://localhost:8080/day2/task7?name=유재석&age=40
    @GetMapping("/task7")   // 쿼리스트링이란?URL(웹주소) 뒤로 ? 작성흐 매개변수명=값&매개변수명=값
    public int method2( @RequestParam String name, @RequestParam int age ){ // 쿼리스트링매개변수명==메소드매개변수명
        // @RequestParam이란? URL(웹주소)안에 포함된 쿼리스트링 매개변수 값 가져오는 어노테이션 /생략가능*
        System.out.println("RestController3.method2");              // soutm : 메소드명 출력 자동완서
        System.out.println("name = " + name + ", age = " + age);    // soutp : 메소드 매개변수 출력 자동완성
        return 3;
    }
    // 3. http://localhost:8080/day2/task8?name=유재석&age=40
    @GetMapping("/task8")
    // 만약에 쿼리스트링의 매개변수명과 자바의 매개변수명이 다르면 @RequestParam( name="쿼리스트링매개변수명" )
    // 만약에 쿼리스트링의 매개변수명을 필수로 하지 않을 경우 @RequestParam( required = false ), 기본값은 true
    public int method3(@RequestParam( required = false ) String name, @RequestParam(name="age")int 나이){
        System.out.println("RestController3.method3");
        System.out.println("name = " + name + ", 나이 = " + 나이);
        return 8;
    }
    // 4. http://localhost:8080/day2/task9?name=유재석
    @DeleteMapping("/task9")
    public int method4(String name,@RequestParam( defaultValue = "19" ) int age){
        // 만약에 @RequestParam을 생략해도 매개변수 매핑(연결 가능)
        // 만약에 쿼리스트링에 매개변수명이 존재하지 않을 때 기본값으로 설정하기, @RequestParam( defaultValue="초기값" )
        System.out.println("RestController3.method4");
        System.out.println("name = " + name + ", age = " + age);
        return 9;
    }
    // 5. http://localhost:8080/day2/task10?name=유재석&age=40
    @DeleteMapping("/task10")   // 만약에 여러개 매개변수를 하나의 Map타입으로 받을수 있다
    public int method5(@RequestParam Map<String, Objects> map){
        System.out.println("RestController3.method5");
        System.out.println("map = " + map); // map = {name=유재석, age=40}
        return 10;
    }
    // 6.
    @PostMapping("/task11")
    public int method6( @ModelAttribute ExamDto examDto ){
        System.out.println("RestController3.method6");
        System.out.println("examDto = " + examDto);
        return 11;
    }
    // 즉] URL?매개변수=값 방식인 쿼리스트링은 URL상 매개변수 노출이 된다
    // GET/DELETE -> 쿼리스트링 -> @ModelAttribute/@RequestParam
    // POST/PUT -> 쿼리스트링/BODY 본문 -> @RequestBody
    // 즉2] URL 상의 매개변수 노출이 가리기 위한 BODY(본문) 사용하자
    //      개인정보나/패스워드/민간한 정보 들은 POST/PUT BODY(본문) 사용하자
    // 예시] 편지의 편지봉투 = 쿼리스트링, 편재의 내용물(편지지) =  BODY

    // 7. http://localhost:8080/day2/task12
    // Body : {"name":"유재석","age":"40"}
    // HTML -> JS -> JAVA( controller -> dao )
    @PostMapping("/task12")
    public int method12( @RequestBody ExamDto examDto ){
        System.out.println("RestController3.method12");
        System.out.println("examDto = " + examDto); // examDto = ExamDto{name='유재석', age=40}
        return 12;
    }
    // 8. http://localhost:8080/day2/task13
    // BODY : {"name":"유재석","age":"40"}
    @PutMapping("/task13")
    public int method13(@RequestBody Map<String, Object> map){
        System.out.println("RestController3.method13");
        System.out.println("map = " + map);
        return 13;
    }
}