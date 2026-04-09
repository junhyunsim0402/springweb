package practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.entity.CategoryEntiy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer categoryId;
    private String category;
    public CategoryEntiy ToEntity(){
        return CategoryEntiy.builder()
                .category(category)
                .build();
    }
}
