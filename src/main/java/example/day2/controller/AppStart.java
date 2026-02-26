package example.day2.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan : 서버를 실행할 때 스프링 컨테이너 등록할 빈(@Component) 들을 동일/하위 패키지 찾아서 등록
// @Component : @Controller @Service @RestController @Repository 등등 몇몇 어노테이션들은 내장됨
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}
// dd