package springweb.member.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.member.dto.MemberDto;
import springweb.member.service.MemberService;

@RestController @RequiredArgsConstructor @RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;
    // [1] 회원가입 post = create = save
    @PostMapping("/signup") public ResponseEntity<?> signup(@RequestBody MemberDto memberDto){
        return ResponseEntity.ok(memberService.signup(memberDto));
    }
    /*
    {
      "mid":"qwe",
      "pwd":"asd",
      "nickname":"준현"
    }
    */
    // [2] 로그인 post = select = find
    @PostMapping("/login") public ResponseEntity<?> login(@RequestBody MemberDto memberDto, HttpSession session){
        // 1] 입력받은 아이디/비밀번호를 서비스에게 보낸다
        boolean result = memberService.login(memberDto);
        // 2] 만약에 로그인 성공이면 세션 부여
        if(result) {
            // 1) 매개변수에 HttpSession session 받는다
            // 2) 로그인 성공한 회원아이디를 세션 객체내 저장, .setAttribute("속성명",속성값)
            session.setAttribute("loginMid", memberDto.getMid());
        }
        // 3] 아니면 실패
        return ResponseEntity.ok(result);
    }
/*
{
  "mid":"qwe",
  "pwd":"asd"
}
*/
    // [3] 로그아웃 == GET == 세션 초기화
    @GetMapping("/logout") public ResponseEntity<?> logout(HttpSession session) {
        // 1) 매개변수에 HttpSession session가져온다
        // 2) 특정한 속성명으로 세션 객체내 속성 삭제, session.removeAttribute("삭제할 속성명")
        session.removeAttribute("loginMid");
        // 3) 반환
        return ResponseEntity.ok(true);
    }

    // [4] 마이페이지 == GET == 현재로그인된 회원정보
    @GetMapping("/myinfo") public ResponseEntity<?> myinfo(HttpSession session){
        // 1) 로그인 상태 확인하기 * 모든 세션객체내 속석값은 Object 타입이다 *
         Object obj = session.getAttribute("loginMid");
         if(obj==null){ return ResponseEntity.ok(false); }
         // 로그인 상태이면 Object --> String
         String loginMid=(String)obj;
         // 로그인 된 mid로 서비스에게 전달하여 로그인된 mid의 dto받아오기
         return ResponseEntity.ok(memberService.myinfo(loginMid));
    }
}
