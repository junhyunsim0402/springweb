package example.day6.practice6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired private MovieService movieService;
    @GetMapping("/movie") public List<MovieDto> 전체조회(){
        List<MovieDto> result=movieService.전체조회();
        return result;
    }
    @GetMapping("movie/detail")
    public MovieDto 개별조회(@RequestParam int movieid){
        MovieDto result=movieService.개별조회(movieid);
        return result;
    }
    @PostMapping("/movie") public boolean 저장(@RequestBody MovieDto movieDto){
        boolean result=movieService.저장(movieDto);
        return result;
    }
    @PutMapping("/movie") public boolean 수정(@RequestBody MovieDto movieDto){
        boolean result= movieService.수정(movieDto);
        return result;
    }
    @DeleteMapping("/movie") public boolean 삭제(@RequestParam int movieid){
        boolean result= movieService.삭제(movieid);
        return result;
    }
}
