package 종합.예제10.board.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.예제10.board.dto.BoardDto;
import 종합.예제10.board.dto.ReplyDto;
import 종합.예제10.board.entity.BoardEntity;
import 종합.예제10.board.entity.ReplyEntity;
import 종합.예제10.board.repository.ReplyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired private ReplyRepository replyRepository;

    // 1. 추가
    public boolean add(ReplyDto replyDto){
        ReplyEntity saved=replyRepository.save(replyDto.toEntity());
        if (saved.getBno()>=1)return true;
        return false;
    }

    // 2. 전체 조회
    public List<ReplyDto> AllPrint(){
        List<ReplyEntity> replyEntityList=replyRepository.findAll();
        List<ReplyDto> replyDtoList=new ArrayList<>();
        replyEntityList.forEach(entity->{
            ReplyDto replyDto=new ReplyDto();
            replyDto.setRno(entity.getRno());
            replyDto.setRcontent(entity.getRcontent());
            replyDto.setRwriter(entity.getRwriter());
            replyDto.setBno(entity.getBno());
            replyDto.setCreate_date(entity.getCreate_date().toString());
            replyDto.setUpdate_date(entity.getUpdate_date().toString());
            replyDtoList.add(replyDto);
        });
        return replyDtoList;
    }

    // 3. 수정
    @Transactional  // 수정시 여러개 setter 사용함으로써 단일 실행하기 위함
    public boolean update(ReplyDto replyDto){
        // 1] 수정할 엔티티의 pk번호를 확인한다
        int updatePk=replyDto.getRno();
        // 2] 수정할 엔티티 찾기 --> 영속성, Optional 반환
        Optional<ReplyEntity> optional = replyRepository.findById(updatePk);
        // 3] 만약에 찾은 엔티티가 존재하면
        if(optional.isPresent()){
            ReplyEntity update = optional.get();// 엔티티 꺼내기
            update.setRcontent(replyDto.getRcontent());
            update.setRwriter(replyDto.getRwriter());
            return true;
        }
        else{ return false; }
    }

    // 5. 삭제
    public boolean delete(int rno){
        Optional<ReplyEntity> optional=replyRepository.findById(rno);
        if(optional.isPresent()){
            replyRepository.deleteById(rno);
            return true;
        }
        return false;
    }
}
