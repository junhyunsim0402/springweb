package example.day5.practice5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class BookDto {
    private Integer bookId;
    private String bookName;
    private String writer;
    private String publisher;
}
