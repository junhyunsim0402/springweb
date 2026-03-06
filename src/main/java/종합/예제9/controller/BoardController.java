package 종합.예제9.controller;

import 종합.예제9.model.dao.BoardDao;
import 종합.예제9.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 빈 등록 + HTTP 기능 + HTTP 응답 객체
public class BoardController {

    // 의존성 주입 : 등록된 빈(객체) 가져오기
    @Autowired private BoardDao boardDao;

    @GetMapping("/board")   // localhost:8080/board     // 해당 메소드의 HTTP 메소드와 주소 정의
    public List<BoardDto> findAll(){                    // 매개변수 정의
        List<BoardDto> result=boardDao.findAll();       // DAO 호출하여 결과 받기
        return result;                                  // DAO결과로 응답
    }

    // [2] 게시물등록
    @PostMapping("/board")
    public boolean write(@RequestBody BoardDto boardDto){
        boolean result=boardDao.write(boardDto);
        return result;
    }

    @PutMapping("/board")
    public boolean update(@RequestBody BoardDto boardDto){
        boolean result=boardDao.update(boardDto);
        return result;
    }
    @DeleteMapping("/board")
    public boolean delete(@RequestParam int bno){
        boolean result= boardDao.delete(bno);
        return result;
    }
}
