package practice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.dto.CategoryDto;
import practice.entity.CategoryEntiy;
import practice.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    // 부서 조회
    public List<CategoryDto> findCategory(){
        List<CategoryEntiy> categoryEntiyList=categoryRepository.findAll();
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        categoryEntiyList.forEach(entity->{
            categoryDtoList.add(entity.CategoryToDto());
        });
        return categoryDtoList;
    }

    // 부서 추가
    public boolean addCatetory(CategoryDto categoryDto){
        CategoryEntiy categoryEntiy=categoryRepository.save(categoryDto.ToEntity());
        if(categoryEntiy.getCategoryId()>0) return true;
        return false;
    }
    // 부서 삭제
    public boolean deleteCategory(Integer categoryId){
        Optional<CategoryEntiy> optional=categoryRepository.findById(categoryId);
        if(optional.isPresent()){
            categoryRepository.deleteById(categoryId);
            return true;
        }
        return false;
    }
    // 부서 수정
    @Transactional
    public boolean updateCategory(Integer categoryId,CategoryDto categoryDto){
        CategoryEntiy categoryEntiy=categoryRepository.findById(categoryId).orElseThrow();
        categoryEntiy.setCategory(categoryDto.getCategory());
        categoryRepository.save(categoryEntiy);
        return true;
    }
}
