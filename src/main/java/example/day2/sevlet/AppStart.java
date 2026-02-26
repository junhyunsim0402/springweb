package example.day2.sevlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@SpringBootApplication  // 스프링부트 환경 의존성(미리 만들어진 코드) 주입
// 모든 코드들은 ctrl + 클릭 -> 해당 라이브러리로 이동
// * WAS(웹서버) 환경 설정이 포함된다.  --> HTTP : localhost:8080 VS 172.16.96.76
@ServletComponentScan   // 서블릿 클래스를 스캔하여 등록한다.
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
        // SpringApplication.run(현재 클래스); : 스프링 실행 함수
        // 현재클래스는 @SpringBootApplication 의존성 주입 받은 상태
        //      즉] 스프링 환경 세팅이 AppStart에 연결된 상태이므로 AppStart 클래스 실행
        // 클래스명.class : 클래스 정보(맴버/함수/상속/구현/생성자 등) 호출
        System.out.println("AppStart.class = " + AppStart.class);
    }
}
