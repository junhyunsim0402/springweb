package practice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.dto.CategoryDto;
import practice.dto.MemberDto;
import practice.service.MemberService;

@RestController @RequiredArgsConstructor @RequestMapping("/member")
@CrossOrigin( value = "http://localhost:5173" )
public class MemberController {
    private final MemberService memberService;
    // 사원 조회
    @GetMapping("/select") public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(memberService.findAll());
    }
    // 사원 추가
    @PostMapping("/add") public ResponseEntity<?> addMember(@ModelAttribute MemberDto memberDto){
        return ResponseEntity.ok(memberService.addMember(memberDto));
    }

    // 사원 수정
    @PutMapping("/update") public  ResponseEntity<?> updateMember(@ModelAttribute MemberDto memberDto){
        return ResponseEntity.ok(memberService.updateMember(memberDto));
    }

    // 사원 삭제
    @DeleteMapping("/delete") public ResponseEntity<?> deleteMember(@RequestParam Integer mno){
        return ResponseEntity.ok(memberService.deleteMember(mno));
    }
}
