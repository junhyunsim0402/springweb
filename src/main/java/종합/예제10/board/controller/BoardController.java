package 종합.예제10.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.예제10.board.dto.BoardDto;
import 종합.예제10.board.service.BoardService;

import java.util.List;

@RestController
public class BoardController {
    @Autowired private BoardService boardService;

    // 등록
    @PostMapping("/board") public boolean add(@RequestBody BoardDto boardDto){
        boolean result= boardService.add(boardDto);
        return result;
    }

    // 전체 조회
    @GetMapping("/board")
    public List<BoardDto> AllPrint(){
        List<BoardDto> result=boardService.AllPrint();
        return result;
    }

    // 개별 조회
    @GetMapping("/board/detail")
    public BoardDto onePrint(@RequestParam int bno){
        BoardDto result=boardService.onePrint(bno);
        return result;
    }

    // 수정
    @PutMapping("/board")
    public boolean update(@RequestBody BoardDto boardDto){
        boolean result= boardService.update(boardDto);
        return result;
    }

    // 삭제
    @DeleteMapping public boolean delete(@RequestParam int bno){
        boolean result= boardService.delete(bno);
        return result;
    }
}
