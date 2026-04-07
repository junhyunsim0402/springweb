package springweb.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import springweb.member.dto.MemberDto;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    // [*] 비크립트(암호화) 객체 생성
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    // [1] 회원가입
    public boolean signup(MemberDto memberDto){
        // 1] 저장할 dto --> entity 변환
        MemberEntity saveEntity=memberDto.toEntity();
        // *** 저장 하기 전에 입력받은 비밀번호를 암호화 *** ///
        String pwd1= passwordEncoder.encode(saveEntity.getPwd());// - 입력받은 패스워드 암호화
        saveEntity.setPwd(pwd1);    // 암호화된 패스워드 대입
        System.out.println("로그인 시도 패스워드: " + memberDto.getPwd());
        // 2] jpa의 save메소드
        MemberEntity saved=memberRepository.save(saveEntity);
        // 3] 확인
        return saved.getMno()>0?true:false;
    }
    // [2] 로그인
    public boolean login(MemberDto memberDto){
        // 1] JPA으로 아이디로 엔티티 찾기, SQL로 아이디/비밀번호 일치여부로 로그인 판단 불가능
        Optional<MemberEntity> optional=memberRepository.findByMid(memberDto.getMid());
        // 2] 만약에 조회된 엔티티가 존재하면
        if(optional.isPresent()){
            // 엔티티 꺼내기
            MemberEntity member=optional.get();
            // 비크립트 암호화로 평문과 암호화문 비교
            // passwordEncoder.matches(평문,암호화);
            return passwordEncoder.matches(memberDto.getPwd(), member.getPwd())?true:false;
        }
        return false;
    }
    // [3] 로그아웃 생략

    // [4] 마이페이지
    public MemberDto myinfo(String loginMid){
        // 1] 로그인된 mid를 찾아서 리포지토리에서 찾는다
        Optional<MemberEntity> optional=memberRepository.findByMid(loginMid);
        // 2] 만약에 엔티티가 존재하면
        if(optional.isPresent()){
            return optional.get().toDto();
        }
        return null;
    }
}
/*
* 암호화
*   1. 정의 : 자료를 보호 하기 위해 사람이 이해하기 어려운 데이터로 변환
*   2. 목적 : 자료보호, 신괴성, 무결성 유지
*   3. 사용처 : 비밀번호, 금융, HTTPS 등등
*   4. 용어 :
*       1) 평문 : 원래의 자료
*       2) 암호문 : 암호화된 자료
*       3) 암호화 : 평문자료를 암호문으로 변환하는 과정
*       4) 복호화 : 암호문 자료를 평문으로 변환하는 과정
*       5) 단방향 암호화 : 평문을 암호문으로 변환하고 다시 평문으로 변환 불가능 < 복호화 없음 >
*       6) 양방향 암호화 : 평문을 암호문으로 변환하고 다시 평문으로 변환 가능 < 암호화/복호화 >
*       7) 해시 함수 : 자료를 고정된 길이로 변환하는 함수
*           - 자바 : .hashCode(), Object클래스의 메소드로 객체 주소값을 해시코드(일정한 길이의 값)로 반환
*           * 서로 다른 자료들을 * 동일한 길이 * 로 변환하는 함수
*           EX)
*           - '사과' ---> A1B2C3
*           - '사과' ---> A1B2C3  * 같은 자료는 같은 해시값으로 나올 수 있다
*           - '바나나' ---> X1C2V3 * 단 서로 다른 자료도 일정한 길이로 변환한다
*           - '파인애플' ---> T1Y2U3
*       8) 솔트 : 암호화할때 사용되는 랜덤값 ( 동일한 계산식(알고리즘/해시) 의 서로 다른 결과값을 )
*           - '사과' --> 솔트추가 --> A1B2C5
*   5. 종류
*       1) 비밀번호 : Bcrypt(비크립트), 해시함수, 단방향/복호화 없음, SHA
*       2) 전자서명/파일 : SHA-256, 해시함수, 단반향/복호화 없음, SHA-512(비트)
*           - bit : 0 또는 1
*       3) 웹 통신 : HTTPS(SSL/TLS), HTTP(암호화 안된 상태) VS HTTPS(암호화 된 상태)
*   6. 비크립트
*       1) 정의 : 해시 함수를 이용하여 주로 비밀번호 암호화 할 때 사용된다
*       2) 특징
*           - 솔트(salt) : 같은 비밀번호라도 랜덤(salt)값으로 서로 다른 암호회된 결과를 만든다
*           - 반복 연산 적용 : 계산식을 여러번 하여 검증 속도를 늦춤
*           - 원본 복구 불가능 : 단방향 암호문으로 비밀번호 찾기 대신에 임시비밀번호 부여/수정
*       3) 형태
*           $2a$10$CLkpavbKZByG.DVAKmXYze(해시 값)
*           - $2a : 비크립트 버전
*           - $10 : 반복연산수, 제곱근
*           - $CLkpavbKZByG.DVAKmXYze : salt(22글자)
*           - 그외 나머지 ( 해시 값 )
*           * 평문과 암호문 비교할때는 암호문의 연산수와 slat와 해시값으로 평문을 암호화해서 비교한다
*       4) 설치 :
*           1. SPRING 시큐리티 포함 : implementation 'org.springframework.boot:spring-boot-starter-security'
*           2. SPRING 시큐리티내 비크립트만 : implementation 'org.springframework.security:spring-security-crypto:6.4.4'
*       5) 사용법
*           private final BCryptPasswordEncoder 암호화객체=new BCryptPasswordEncoder();
*           - String 암호화된값=암호화객체.encode( 암호화할 자료 )
*           - boolean 비교결과 = 암호객체.matches( 평문, 암호화 )
* */