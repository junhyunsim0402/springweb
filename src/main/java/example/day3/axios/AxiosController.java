package example.day3.axios;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/day3/task")
@RestController
public class AxiosController {
    @GetMapping
    public void method1(){
        System.out.println("AxiosController.method1");
    }
    @DeleteMapping
    public int method2(@RequestParam String name){
        System.out.println("AxiosController.method2");
        return 10;
    }
    @PostMapping
    public boolean method3( @RequestParam int age ){
        System.out.println("AxiosController.method3");
        return true;
    }
    @PutMapping
    public boolean method4( @RequestBody Map<String, Object> map){
        System.out.println("AxiosController.method4");
        System.out.println("map = " + map);
        return true;
    }
    @GetMapping("/axios")
    public boolean method5( @RequestParam String name ){
        System.out.println("AxiosController.method5");
        System.out.println("name = " + name);
        return true;
    }

}
