package practice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.dto.CategoryDto;
import practice.dto.MemberDto;
import practice.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@CrossOrigin( value = "http://localhost:5173" )
public class CategoryController {
    private final CategoryService categoryService;
    // 부서 조회
    @GetMapping("/select") public ResponseEntity<?> finCdategory(){
        return ResponseEntity.ok(categoryService.findCategory());
    }

    // 사원 추가
    @PostMapping("/add") public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.addCatetory(categoryDto));
    }
    // 부서 수정
    @PutMapping("/update/{categoryId}") public  ResponseEntity<?> updateMember(@PathVariable Integer categoryId,@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId,categoryDto));
    }

    // 부서 삭제
    @DeleteMapping("/delete{categoryId}") public ResponseEntity<?> deleteMember(@PathVariable Integer categoryId){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }
}
