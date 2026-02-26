package example.day3.practice3;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDto {
    private Integer ano;
    private String studentName;
    private String date;
    private String status;
}