package practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import practice.entity.MemberEntity;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class MemberDto {
    private Integer mno;
    private Integer categoryId;
    private String category;
    private String position;    // 직급
    private String mname;
    private String mfile;
    private MultipartFile uploadFile;
    public MemberEntity ToEntity(){
        return MemberEntity.builder()
                .mno(mno)
                .mname(mname)
                .position(position)
                .mfile(mfile)
                .build();
    }
}
