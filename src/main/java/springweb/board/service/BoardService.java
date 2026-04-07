package springweb.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import springweb.board.dto.BoardDto;
import springweb.board.entity.BoardEntity;
import springweb.board.repository.BoardRepository;
import springweb.member.entity.MemberEntity;
import springweb.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;
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
        //  최종 DB에 엔티티를 SAVE하기전에 첨부파일이 존재하면 업로드 + //
        String fileName=fileService.upload(boardDto.getUploadFile());
        // 만약에 업로드 했다면 저장할 엔티티에 업로드된 파일명 저장하기
        if(fileName!=null){ saveEntity.setBfile(fileName); }
        // 2. entity 저장
        BoardEntity savedEntity = boardRepository.save(saveEntity);
        if(savedEntity.getBno()>0){ return true; }
        else{return false;}
    }
    // [2] 전체조회
    public List<BoardDto> findAll(){
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"bno"))
                .stream()   // 여러개 자료를 갖는 자료(리스트/배열) 들의 순차적 처리 지원 함수
                // .map(BoardEntity -> {return boardEntity.toDto();})    // map(반복변수-> return 실행문)
                .map(BoardEntity::toDto)    // 메소드 레퍼런스란, 화살표 함수 간결하게 사용하는 문법, 클래스명::함수명
                .toList();  // 리스트타입으로 반환

    }
    // [3] 개별조회
    public BoardDto findById(Long bno){
        return boardRepository.findById(bno)
                .orElse(null)   // 만일 엔티티가 없으면
                .toDto();   // 존재하면 엔티티를 dto로 반환
    }
}
