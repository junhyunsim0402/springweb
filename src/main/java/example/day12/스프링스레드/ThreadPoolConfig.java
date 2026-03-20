package example.day12.스프링스레드;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration  // IOC 빈 어노테이션 종류 중 하나, 빈 등록
// 주로 미리 만들어진 설정값을 오버라이딩/등록 코드
public class ThreadPoolConfig {
    // ******* 서버의 안정화 ******* //
    @Bean   // 해당 함수를 등록, 클래스 자체 등록 하는것과 다르게 함수/필드 단위로 별도 빈 등록
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);    // 기본(최소) 실행 스레드 개수 설정
        executor.setMaxPoolSize(3);     // 최대 실행 스레드 개수 설정
        // 만일 5개 이상의 요청 및 스레드 사용시 이후 요청은 대기 상태
        executor.setQueueCapacity(3);
        // queue : 요청의 대기열(웨이팅 줄)
        executor.initialize();  // 스래드풀 초기화
        return executor;
    }
}
