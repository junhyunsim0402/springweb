package practice.entity;

import jakarta.persistence.*;
import lombok.*;
import practice.dto.CategoryDto;
import practice.dto.MemberDto;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryEntiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(nullable = false, unique = true)
    private String category;

    public CategoryDto CategoryToDto(){
        return CategoryDto.builder()
                .categoryId(categoryId)
                .category(category)
                .build();
    }
}
