package example.day2.practice2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component// (1)싱글톤 대신에 스프링컨테이너 빈(객체) 등록
//@Controller // (2) HTTP 기능( GET/POST/PUT/DELETE )
//@ResponseBody// (3) HTTP 응답 Content-Type 자동 설정
@RestController // @Controller(+@Component) + @ResponseBody
@RequestMapping("/practice2/post") // 해당 클래스내 메소드들이 정의하는 URL 공통 (+선택)
public class PostController {
    /*
        메소드 이란? 상호작용(주고받는 쌍방향 행동)
            매개변수? 매개(중개)변수(정해져있지않는값) : 메소드가 호출될때 들어오는 값 (인수)
            반환값? 메소드가 종료될때 호출했던곳으로 반환해주는 값 (리턴)
        HTTP 이란? 상호작용( request / response )
            request? 클라이언트가 서버로 부터 요청
            response? 서버가 처리한 결과를 클라이언트에 반환/응답
    */
    // 1. http://localhost:8080/practice2/post
    @PostMapping // localhost=내컴퓨터 , :8080 = WAS포트번호 , /practice2/post
    public boolean 글쓰기( ){
        System.out.println("PostController.글쓰기");
        return true; // 임의의값
    }
    // 2. http://localhost:8080/practice2/post
    // 컬렉션 프레임워크 , [ ] 1개 : List 1개 , { } 1개 : Map 1개
    // Map 이란? key 와 value 한쌍으로 엔트리 갖고 여러개 엔트리 저장한다. <--> DTO/JSON
    // List< PostDto > VS List< Map< String,Object> >
    @GetMapping
    public List< Map< String, Object> > 전체글조회( ){
        System.out.println("PostController.전체글조회");
        List< Map<String,Object> > list = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put( "pno" , "1" ); map1.put( "ptitle" , "제목1");
        Map<String,Object> map2 = new HashMap<>();
        map2.put( "pno" , "2" ); map2.put( "ptitle" , "제목2");
        list.add( map1 ); list.add( map2 );
        return list;
    }
    // 3.http://localhost:8080/practice2/post/view
    @GetMapping("/view")
    public Map<String,Object> 개별글조회( ){
        System.out.println("PostController.개별글조회");
        Map<String,Object> map = new HashMap<>();
        map.put( "pno" , "3" ); map.put( "ptitle" , "제목3");
        return map;
    }
    // 4.http://localhost:8080/practice2/post
    @PutMapping
    public boolean 개별글수정( ){
        System.out.println("PostController.개별글수정");
        return true;
    }
    // 5. http://localhost:8080/practice2/post
    @DeleteMapping
    public int 개별글삭제( ){
        System.out.println("PostController.개별글삭제");
        return 3;
    }
} // class end