package example.day6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@AllArgsConstructor@NoArgsConstructor
@Data
@Builder
@Table(name = "goods")  // 생략시 클래스명으로 자동설정
public class GoodsEntity extends BaseTime{
    @Id // JPA 는 엔티티내 1개 이상의 기본키 필수로 한다
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto_increament
    private Integer gno;    // 제품번호

    @Column(name = "제품명", nullable = false,length = 100,unique = true,insertable = true,updatable = true)
    private String gname;   // 제품명

    // @Column // 생략가능시 : 자바의 타입 --> SQL타입 자동 변경, 자바의 변수명 --> SQL필드명으로 자동 설정
    private Integer gprice; // 제품가격

    @Column(columnDefinition = "varchar(100) default '제품설명' not null")
    private String gdesc;   // 제품설명

    // ENTITY --> DTO 변환함수
    public GoodsDto toDto(){
        return GoodsDto.builder()
                .gno(gno)
                .gname(gname)
                .gprice(gprice)
                .gdesc(gdesc)
                .createDate(getCreateDate().toString())
                .updateDate(getUpdateDate().toString())
                .build();
    }
}
/*
* @Id : 기본키 설정
* @@GeneratedValue(strategy = GenerationType.IDENTITY) : auto_increament
* @Column()
*   name="필드명",     기본값은 자바필드명
*   nullable=false,     기본값은 true, not null
*   length=길이,         기본값은 255, varchar(길이)
*   unique=true,        기본값은 false, 중복여부
*   insertable=trie,    기본값은 true, insert할때 적용여부
*   updatable=true,     기본값은 true,update할때 적용여부
*   columnDefinition : "SQL",   JPA가 아닌 네이티브( 실제 SQL )  쿼리 작성
* 레코드 생성( 회원가입/등록일/주문일 등) 날짜 / 수정날짜
* */