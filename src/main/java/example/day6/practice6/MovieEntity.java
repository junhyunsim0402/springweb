package example.day6.practice6;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder@Data@Table(name = "movie")
@Entity@AllArgsConstructor@NoArgsConstructor
public class MovieEntity extends MovieBaseTime{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieid;

    @Column(name = "영화제목", nullable = false,length = 100,unique = true,insertable = true,updatable = true)
    private String title;

    private String director;
    private Integer rating;
    private String releasedate;

    public MovieDto toDto(){
        return MovieDto.builder()
                .movieid(movieid)
                .title(title)
                .director(director)
                .rating(rating)
                .releasedate(releasedate)
                .createDate(getCreateDate()!=null ? getCreateDate().toString() : null)
                .updateDate(getUpdateDate()!=null ? getUpdateDate().toString() : null)
                .build();
    }
}
