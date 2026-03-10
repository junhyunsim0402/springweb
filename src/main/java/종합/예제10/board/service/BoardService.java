package 종합.예제10.board.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.예제10.board.dto.BoardDto;
import 종합.예제10.board.entity.BoardEntity;
import 종합.예제10.board.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired private BoardRepository boardRepository;

    // 1. 추가
    public boolean add(BoardDto boardDto){
        BoardEntity saved=boardRepository.save(boardDto.toEntity());
        if (saved.getBno()>=1)return true;
        return false;
    }

    // 2. 전체 조회
    public List<BoardDto> AllPrint(){
        List<BoardEntity> boardEntityList=boardRepository.findAll();
        List<BoardDto> boardDtoList=new ArrayList<>();
        boardEntityList.forEach(entity->{
            BoardDto boardDto=new BoardDto();
            boardDto.setBno(entity.getBno());
            boardDto.setBtitle(entity.getBtitle());
            boardDto.setBcontent(entity.getBcontent());
            boardDto.setBwriter(entity.getBwriter());
            boardDto.setCreate_date(entity.getCreate_date().toString());
            boardDto.setUpdate_date(entity.getUpdate_date().toString());
            boardDtoList.add(boardDto);
        });
        return boardDtoList;
    }

    // 3. 개별조회
    public BoardDto onePrint(int bno){
        Optional<BoardEntity> optional=boardRepository.findById(bno);
        if(optional.isPresent()){
            BoardEntity entity=optional.get();
            return entity.toDto();
        }
        return null;
    }

    // 4. 수정
    @Transactional  // 수정시 여러개 setter 사용함으로써 단일 실행하기 위함
    public boolean update(BoardDto boardDto){
        // 1] 수정할 엔티티의 pk번호를 확인한다
        int updatePk=boardDto.getBno();
        // 2] 수정할 엔티티 찾기 --> 영속성, Optional 반환
        Optional<BoardEntity> optional = boardRepository.findById(updatePk);
        // 3] 만약에 찾은 엔티티가 존재하면
        if(optional.isPresent()){
            BoardEntity update = optional.get();// 엔티티 꺼내기
            update.setBtitle(boardDto.getBtitle());
            update.setBcontent(boardDto.getBcontent());
            update.setBwriter(boardDto.getBwriter());
            return true;
        }
        else{ return false; }
    }

    // 5. 삭제
    public boolean delete(int bno){
        Optional<BoardEntity> optional=boardRepository.findById(bno);
        if(optional.isPresent()){
            boardRepository.deleteById(bno);
            return true;
        }
        return false;
    }
}
