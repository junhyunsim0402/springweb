package example.day13;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service @RequiredArgsConstructor
public class ApiService {
    // 공공데이터 기준 : 로그인 -> 마이페이지
    String serviceKey="4ee9be39b33128b90223f3a882e6ae3c210fa69d2c15156eda7335e4e2687bcc";
    // web 요청 객체 만들기, WebClient 이용한 외부 HTTP 요청/응답
    private final WebClient webClient= WebClient.builder().build();

    // [1] 인천광역시 부평구_맛있는 집(맛집) 현황 JSON, * 서비스키를 주소상에 쿼리스트링으로 포함하는 경우 *
    // https://api.odcloud.kr/api/15103411/v1/uddi:b7c1c017-1d8d-4b19-8bec-c91a13927ea2?page=1&perPage=10&serviceKey=4ee9be39b33128b90223f3a882e6ae3c210fa69d2c15156eda7335e4e2687bcc
    public Map<String,Object> test1(){
        String url1="https://api.odcloud.kr/api/15103411/v1/uddi:b7c1c017-1d8d-4b19-8bec-c91a13927ea2";
        url1 += "?serviceKey="+serviceKey;
        url1 += "&page=1";
        url1 += "&perPage=67";
        return webClient.get()
                .uri(url1)  // 요청할 api 주소 넣어준다. url vs uri(매개변수 포함)
                .retrieve() // 반환/통신/응답 결과 수신 함수
                .bodyToMono(Map.class)   // 반환 값을 자바 타입으로 변환, 즉] 반환타입이 JSON이면 MAP으로 받는다
                .block();   // 동기(처리가 끝날때 까지 대기 상태) 방식으로 결과 반환
    }

    // [2] 	국립중앙의료원_전국 약국 정보 조회 서비스 XML, * 서비스키를 주소상에 포함하지 않고 HTTP header에 포함하는 경우 *
    // https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=4ee9be39b33128b90223f3a882e6ae3c210fa69d2c15156eda7335e4e2687bcc&pageNo=1&numOfRows=10
    public Map<String,Object> test2() {
        String url2 = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
        url2+="?pageNo=1";  //페이지번호
        url2+="&numOfRows=10"; // 페이지당 개수
        url2+="&serviceKey="+serviceKey;
        String response=webClient.get()
                .uri(url2)  // 통신할 주소
                // .header("Authorization","Infuser "+serviceKey)
                // HTTP 헤더란? HTTP 통신할때 부가정보 포함하는 정보
                // 주로 인증 관련된 정보들을 포함하는 경우가 있다, API키, 로그인상태
                .retrieve() // 통신 결과/응답 수신/받기
                .bodyToMono(String.class)  // 반환 타입, XML --> String으로 받기
                .block();   // 동기통신
        // * String(XML) --> Map/Dto 반환, JACKSON //
        try {
            XmlMapper xmlMapper = new XmlMapper();    // xml 매퍼 객체 생성
            // xmlMapper.readValue(변환할 값, 변환할 타입.class )
            Map<String,Object> map=xmlMapper.readValue(response, Map.class);    // String(XML) --> Map 타입 변환
            return map;
        }catch (Exception e) { System.out.println("e = " + e); }
        return null;
    }
}
/*
 * API : 데이터 주고받고 기능을 공유할 수 있는 규칙/프로토콜( HTTP )
 * REST API : HTTP 기반의 API
 * 종류
 *   1. 개발 : SPRING CONTROLLER
 *   2. 활용 : 무료/유로 판단
 *       1) 공공데이터 포털 API
 *       2) LLM(AI 모델) API (비설치/비학습)
 *       3) 사기업
 *           카카오(지도, 로그인 등)
 *           네이버(로그인, 데이터랩 등)
 *           구글(로그인/자동입력방지/캡차)
 *           번역( DeepL, 파파고 )
 *           결제 ( 테스트 : 카카오페이,아임포트 )
 *           등등
 *  반환 타입 : XML / JSON( 변환 필요 없음 )
 *  스프링에서 외부HTTP 요청, 프로잭트/서비스1 <--통신--> 프로젝트/서비스2
 *      1) https://start.spring.io/
 *      2) Spring Reactive Web
 *      3) implementation 'org.springframework.boot:spring-boot-starter-webflux'
 *          - controller : 서버입장의 통신 받는 곳
 *          - webflux : 서버입장에서 먼저 통신 요총
 * XML이란? <> 괄호 사용한 마크업 언어
 *      - 스프링(자바)에서 타입변환이 필요하다. 왜? 자바는 <>를 사용하지 않는다
 *      -
 * */
