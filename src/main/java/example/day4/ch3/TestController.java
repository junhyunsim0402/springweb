package example.day4.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired TestService testService;// DI 의존성 주입
    @GetMapping("/test") public List<Member> getAllMembers(){
        List<Member> members=testService.getAllMembers();
        return members;
    }

    @GetMapping("/test2")
    public boolean saveMember(){
        boolean result=testService.saveMember();
        return result;
    }
}
