package example.day5.practice5;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")
@Data@AllArgsConstructor@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Integer bookId;

    @Column(name = "bookName",nullable = false)
    private String bookName;

    @Column(name = "writer",nullable = false)
    private String writer;
    @Column(name = "publisher")
    private String publisher;
}
