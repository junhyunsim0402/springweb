package example.day5.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExamDto {
    private Integer eno;// int 대신 Integer
    private String ename;
}
