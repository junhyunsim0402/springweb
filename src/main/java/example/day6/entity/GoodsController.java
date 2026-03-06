package example.day6.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.예제10.board.dto.BoardDto;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired private GoodsService goodsService;

    // 저장
    @PostMapping("/goods")
    public boolean 저장(@RequestBody GoodsDto goodsDto){
        boolean result=goodsService.저장(goodsDto);
        return result;
    }

    // 전체조회
    @GetMapping("/goods")
    public List<GoodsDto> 전체조회(){
        List<GoodsDto> result=goodsService.전체조회();
        return result;
    }

    @GetMapping("/goods/detail")
    public GoodsDto 개별조회(@RequestParam int gno){
        GoodsDto result=goodsService.개별조회(gno);
        return result;
    }

    // 수정
    @PutMapping("/goods")
    public boolean 수정(@RequestBody GoodsDto goodsDto){
        boolean result=goodsService.수정( goodsDto);
        return result;
    }
}
