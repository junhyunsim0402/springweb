package example.day6.entity;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {
    @Autowired private GoodsRepository goodsRepository;

    // 1. 저장
    public boolean 저장(GoodsDto goodsDto){
        // 1] dto --> entity
        // GoodsEntity goodsEntity=goodsDto.toEntity();
        // GoodsEntity saved=goodsRepository.save(goodsEntity);

        // 2] JPA save이용하여 엔티티 insert 하기
        GoodsEntity saved=goodsRepository.save(goodsDto.toEntity());

        // 3] save 결과에 pk여부 성공 판단
        if (saved.getGno()>=1)return true;
        return false;
    }

    public List<GoodsDto> 전체조회(){
        List<GoodsEntity> goodsEntityList=goodsRepository.findAll();
        List<GoodsDto> goodsDtoList=new ArrayList<>();
        goodsEntityList.forEach(entity->{
            GoodsDto goodsDto=new GoodsDto();
            goodsDto.setGno(entity.getGno());
            goodsDto.setGprice(entity.getGprice());
            goodsDto.setGname(entity.getGname());
            goodsDto.setGdesc(entity.getGdesc());
            goodsDto.setCreateDate(entity.getCreateDate().toString());
            goodsDto.setUpdateDate(entity.getUpdateDate().toString());
            goodsDtoList.add(goodsDto);
        });
        return goodsDtoList;
    }

    public GoodsDto 개별조회(int bno){
        Optional<GoodsEntity> optional=goodsRepository.findById(bno);
        if(optional.isPresent()){
            GoodsEntity entity=optional.get();
            return entity.toDto();
        }
        return null;
    }

    // 2. 수정
    @Transactional  // 수정시 여러개 setter 사용함으로써 단일 실행하기 위함
    public boolean 수정(GoodsDto goodsDto){
        // 1] 수정할 엔티티의 pk번호를 확인한다
        int updatePk=goodsDto.getGno();
        // 2] 수정할 엔티티 찾기 --> 영속성, Optional 반환
        Optional<GoodsEntity> optional = goodsRepository.findById(updatePk);
        // 3] 만약에 찾은 엔티티가 존재하면
        if(optional.isPresent()){
            GoodsEntity update = optional.get();// 엔티티 꺼내기
            update.setGname(goodsDto.getGname());
            update.setGdesc(goodsDto.getGdesc());
            update.setGprice(goodsDto.getGprice()); // 3개중에 setter에서 문제 발생시 전체 취소
            return true;
        }
        else{ return false; }
    }
    public boolean 삭제(int gno){
        Optional<GoodsEntity> optional=goodsRepository.findById(gno);
        if(optional.isPresent()){
            goodsRepository.deleteById(gno);
            return true;
        }
        return false;
    }
}
