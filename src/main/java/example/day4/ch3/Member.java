package example.day4.ch3;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 빈 생성자이면서 protected접근제한자
@AllArgsConstructor                                 // 전체 매개변수를 갖는 생성자
@Entity                                             //  데이터베이스의 테이블의 레코드와 매핑(연결) 기술 : ORM( 자바객체 <--> DB레코드)
@Getter                                             // getter(자동생성) 메소드
public class Member {
    @Id                                             // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "id",updatable = false)              // 필드/속성 설정, 수정 불가능
    private Long id;

    @Column(name = "name",nullable = false)             // 필드/솔성명 설정, not null
    private String name;
}
