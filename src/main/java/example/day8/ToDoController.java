package example.day8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ToDoController {
    @Autowired private ToDoRepository toDoRepository;
    @PostMapping("/") public boolean 등록하기(@RequestBody ToDoEntity toDoEntity){
        toDoRepository.save(toDoEntity);
        return true;
    }
    @GetMapping("/")
    public List<ToDoEntity> 조회하기(){
        return toDoRepository.findAll();
    }
}
