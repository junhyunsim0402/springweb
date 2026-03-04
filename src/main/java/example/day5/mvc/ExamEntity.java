package example.day5.mvc;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 해당 객체는 데이터베이스 연동 하겠다는 뜻
@Table(name="exam") // 데이터베이스에서 테이블명 정의
@Data@AllArgsConstructor@NoArgsConstructor
public class ExamEntity {
    @Id // 기본키 적용
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increament
    private Integer eno;

    @Column(name = "ename",length = 255,nullable = false) // 속성이름:"ename",길이:255,null허용:X,
    private String ename;
}
// Entitiy 데이터베이스 테이블과 객체 간 연동 객체