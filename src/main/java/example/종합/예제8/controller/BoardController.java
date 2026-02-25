package example.종합.예제8.controller;

import example.종합.예제8.model.dao.BoardDao;
import example.종합.예제8.model.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController // 해당 컨트롤러는 HTTP웹 기능 갖게 된다. < 싱글톤 유사 포함 >
public class BoardController {
    // ** 싱글톤 지우기 **

    private BoardDao bd = BoardDao.getInstance();

    // [2] 게시물 전체 조회 controller , 여러개 레코드 조회 -> ArrayList , 한개 레코드 조회 -> dto
    @GetMapping // 해당 메소드의 HTTP웹 통신 기능 갖게 된다.
    public ArrayList<BoardDto> findAll(){
        ArrayList<BoardDto> result = bd.findAll();
        return result;
    }
    // [1] 게시물 등록 controller
    @PostMapping
    public boolean write( String bcontent , String bwriter ){
        boolean result = bd.write( bcontent , bwriter );
        return result;
    }
    // [4] 게시물 삭제 controller
    @DeleteMapping
    public boolean delete( int bno ){
        boolean result = bd.delete( bno );
        return result;
    }
    // [3] 게시물 수정 controller
    @PutMapping
    public boolean update( int bno , String bcontent ){
        boolean result = bd.update( bno , bcontent );
        return result;
    }


} // class end