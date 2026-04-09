package practice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import practice.dto.MemberDto;

@Entity @AllArgsConstructor @NoArgsConstructor @Builder @Data
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mno;
    @Column(nullable = false)
    private String mname;
    @Column(nullable = false)
    private String position;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryEntiy categoryEntiy;

    @Column(length = 255)
    private String mfile;
    public MemberDto ToDto(){
        return MemberDto.builder()
                .mno(mno)
                .mname(mname)
                .position(position)
                .categoryId(categoryEntiy!=null ? categoryEntiy.getCategoryId() : null)
                .category(categoryEntiy != null ? categoryEntiy.getCategory() : null)
                .mfile(mfile)
                .build();
    }
}
