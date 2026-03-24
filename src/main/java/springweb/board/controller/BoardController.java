package springweb.board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springweb.board.dto.BoardDto;
import springweb.board.service.BoardService;

@RestController @RequiredArgsConstructor @RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    // [1] 회원제 글 등록
    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardDto boardDto, HttpSession session){
        // 1. 세션내 로그인정보 확인
        Object object=session.getAttribute("loginMid");
        if(object==null){ return ResponseEntity.ok(false); }
        // 2. 로그인중이면
        String loginMid=(String)object;
        // 3. 서비스에게 입력받은 값과 세션에 저장된 값 전달한다
        boolean result = boardService.write(boardDto,loginMid);
        return ResponseEntity.ok(result);
    }
        /*{
          "btitle":"qwe",
          "bcontent":"asd"
    }*/
}
