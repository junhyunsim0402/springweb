package 종합.예제10.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.예제10.board.dto.ReplyDto;
import 종합.예제10.board.service.ReplyService;

import java.util.List;

@RestController
public class ReplyController {
    @Autowired private ReplyService replyService;
    @PostMapping("/reply") public boolean add(@RequestBody ReplyDto replyDto){
        boolean result= replyService.add(replyDto);
        return result;
    }
    @GetMapping("/reply") public List<ReplyDto> AllPrint(){
        List<ReplyDto> result=replyService.AllPrint();
        return result;
    }
    @PutMapping("/reply") public boolean update(@RequestBody ReplyDto replyDto){
        boolean result=replyService.update(replyDto);
        return result;
    }
    @DeleteMapping("/reply") public boolean delete(@RequestParam int rno){
        boolean result=replyService.delete(rno);
        return result;
    }
}
