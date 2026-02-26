package example.day3.practice3;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @PostMapping
    public boolean 출석등록(@RequestBody AttendanceDto attendanceDto) {
        System.out.println("AttendanceController.출석등록");
        System.out.println("attendanceDto = " + attendanceDto);
        return true;
    }

    @GetMapping
    public List<AttendanceDto> 전체조회() {
        List<AttendanceDto> lists = new ArrayList<>();
        lists.add(new AttendanceDto(1,"유재석","2026-02-26","출석"));
        lists.add(AttendanceDto.builder()
                .date("2026-02-26").status("출석").studentName("홍길동").ano(1).build());
        return lists;
    }

    @GetMapping("/detail")
    public AttendanceDto 개별조회(@RequestParam int ano) {
        return AttendanceDto.builder().ano(ano)
                .date("2026-02-26").status("출석").studentName("홍길동").build();
    }
    @DeleteMapping
    public boolean 삭제(@RequestParam int ano) {
        return true;
    }

    @PutMapping
    public boolean 수정(@RequestBody AttendanceDto dto) {
        return true;
    }
}