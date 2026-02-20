package example.day1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // 주의 : SpringBootApplication -> SpringApplication
        SpringApplication.run(AppStart.class);
    }
}
