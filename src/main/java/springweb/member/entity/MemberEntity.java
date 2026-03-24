package springweb.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import springweb.member.dto.MemberDto;

@Entity @Table(name = "member") @NoArgsConstructor @AllArgsConstructor @Data @Builder
public class MemberEntity extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;   // 회원번호

    @Column(nullable = false,unique = true,length = 100)
    private String mid; // 회원아이디

    @Column(nullable = false)
    private String pwd; // 회원 비밀번호

    @Column(nullable = false,length = 30)
    private String nickname;    // 회원 닉네임

    // + Entity --> Dto , 주로 조회할 때 사용
    public MemberDto toDto(){
        return MemberDto.builder()
                .mno( mno )
                .mid( mid )
                //.mpwd( mpwd ) // 주로 패스워드는 DTO로 반환하지 않는다.
                .nickname( nickname )
                .createDate( getCreateDate().toString() )
                .updateDate( getUpdateDate().toString() )
                .build();
    }
}
