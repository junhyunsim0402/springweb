package example.day15;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service @RequiredArgsConstructor
public class JwtService {
    // * 내가 만든 임의의 값으로 토큰에 사용되는 암호화 계산식의 비밀번호
    private final String 비밀번호="123456789123456789123456789123456789";
    // * 내가 만든 임의의 값(비밀번호) 해시값으로 변환
    private final Key 비밀키= Keys.hmacShaKeyFor(비밀번호.getBytes());
    // [1] JWT 토큰 생성 : 사용자의 정보를 JSON형식으로 암호화
    public String 토큰생성(String data){
        String token= Jwts.builder()    // 토큰 생성 시작
                // .claim("key",value)  토큰에 저장할 자료를 key와 value에 대입한다
                .claim("data",data)
                .setIssuedAt(new Date())// 토큰 발급날짜/시간, new Data() : 시스템날짜/시간 반환 객체
                // .setExpiration( new Date(System.currentTimeMillis()+1000) * 초단위 )
                .setExpiration( new Date(System.currentTimeMillis()+1000 * 20))        // 토큰 유지/유효 시간, 초단위
                // .signWith("비밀키",암호화알고리즘)
                .signWith(비밀키, SignatureAlgorithm.HS256)   // 최종 토큰 암호화는 HS256알고리즘 적용
                .compact();             // 토큰 최종 문자열로 반환
        return token;
    }
    // [2] 값 추출
    public String 값추출(String 토큰) {
        try {
            Claims claims = Jwts.parser() // 파싱 / 가져온다는 뜻
                    .setSigningKey(비밀키) // 서명 검증에 팔요한 비밀키 대입
                    .build()    // 비밀키 확인
                    .parseClaimsJws(토큰) // 검증할 토큰 대입
                    .getBody(); // 검증 성공시 클레임(내용물) 가져온다
            return (String) claims.get("data");   // 저장된 값은 무조건 Object타입이다
        } catch (Exception e) { System.out.println("e = " + e); }
        return null;
    }
}
/*
    JWT( json web token(징표) )
        1. 정의 : JSON형식의 데이터를 사용하기 위한 토큰 기반의 인증 형식
        2. 목적 : 웹/앱 에서 인증 과 권한부여/확인 사용(클라이언트) VS 세션(서버)

    사용법
        1) 설치
            implementation 'io.jsonwebtoken:jjwt-api:0.12.6'    // JWT
            runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.12.6'      // JWT
            runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.12.6'   // JWT





*/