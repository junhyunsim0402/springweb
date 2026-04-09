package practice.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.dto.MemberDto;
import practice.entity.CategoryEntiy;
import practice.entity.MemberEntity;
import practice.repository.CategoryRepository;
import practice.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final FileService fileService;
    // 사원 조회
    public List<MemberDto> findAll(){
        List<MemberEntity> memberEntityList=memberRepository.findAll();
        List<MemberDto> memberDtoList=new ArrayList<>();
        memberEntityList.forEach(entity->{
            memberDtoList.add(entity.ToDto());
        });
        return memberDtoList;
    }
    // 사원 추가
    public boolean addMember(MemberDto memberDto){
        String mfile= fileService.upload(memberDto.getUploadFile());
        memberDto.setMfile(mfile);
        MemberEntity memberEntity=memberRepository.save(memberDto.ToEntity());
        if(memberEntity.getMno()>0) return true;
        return false;
    }
    // 사원 삭제
    public boolean deleteMember(Integer mno){
        Optional<MemberEntity> optional=memberRepository.findById(mno);
        if(optional.isPresent()){
            memberRepository.deleteById(mno);
            return true;
        }
        return false;
    }

    // 사원 수정
    @Transactional
    public boolean updateMember(MemberDto memberDto){
        MemberEntity memberEntity=memberRepository.findById(memberDto.getMno())
                .orElseThrow(()->new EntityNotFoundException("엔티티 없음"));
        memberEntity.setMname(memberDto.getMname());
        memberEntity.setPosition(memberDto.getPosition());
        memberEntity.setMfile(memberDto.getMfile());
        return true;
    }
}
