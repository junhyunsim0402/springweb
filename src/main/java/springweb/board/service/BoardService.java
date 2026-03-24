package springweb.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springweb.board.dto.BoardDto;
import springweb.board.entity.BoardEntity;
import springweb.board.repository.BoardRepository;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;;
    // [1] 회원제 글 등록
    public boolean write(BoardDto boardDto,String loginMid){
        // 1. dto -> entity
        BoardEntity saveEntity=boardDto.toEntity();
        // ** 저장하기 전에 FK 대입, FK의 엔티티를 찾아서 대입 ** //
        // 현재 로그인중인 mid로 엔티티 찾기
        Optional<MemberEntity> optional=memberRepository.findByMid(loginMid);
        if(!optional.isPresent()){ return false; }
        // 저장할 게시물 엔티티에 set참조엔티티(회원엔티티);
        saveEntity.setMemberEntity(optional.get());
        // 2. entity 저장
        BoardEntity savedEntity = boardRepository.save(saveEntity);
        if(savedEntity.getBno()>0){ return true; }
        else{return false;}
    }
}
