package example.day6.practice6;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired private MovieRepository movieRepository;

    public List<MovieDto> 전체조회(){
        List<MovieEntity> movieEntityList=movieRepository.findAll();
        List<MovieDto> movieDtoList=new ArrayList<>();
        movieEntityList.forEach(entity->{
            MovieDto movieDto=new MovieDto();
            movieDto.setMovieid(entity.getMovieid());
            movieDto.setTitle(entity.getTitle());
            movieDto.setDirector(entity.getDirector());
            movieDto.setReleasedate(entity.getReleasedate());
            movieDto.setRating(entity.getRating());
            movieDto.setCreateDate(entity.getCreateDate().toString());
            movieDto.setUpdateDate(entity.getUpdateDate().toString());
            movieDtoList.add(movieDto);
        });
        return movieDtoList;
    }

    public MovieDto 개별조회(int movieid){
        Optional<MovieEntity> optional=movieRepository.findById(movieid);
        if(optional.isPresent()){
            MovieEntity entity=optional.get();
            return entity.toDto();
        }
        return null;
    }

    public boolean 저장(MovieDto movieDto){
        MovieEntity saved=movieRepository.save(movieDto.toEntity());
        if(saved.getMovieid()>=1)return true;
        return false;
    }
    @Transactional
    public boolean 수정(MovieDto movieDto){
        int updatePk=movieDto.getMovieid();
        Optional<MovieEntity> optional=movieRepository.findById(updatePk);
        if(optional.isPresent()){
            MovieEntity update=optional.get();
            update.setTitle(movieDto.getTitle());
            update.setDirector(movieDto.getDirector());
            update.setReleasedate(movieDto.getReleasedate());
            update.setRating(movieDto.getRating());
            return true;
        }
        else {return false;}
    }

    public boolean 삭제(int movieid){
        Optional<MovieEntity> optional=movieRepository.findById(movieid);
        if(optional.isPresent()) {
            movieRepository.deleteById(movieid);
            return true;
        }
        return false;
    }

}
