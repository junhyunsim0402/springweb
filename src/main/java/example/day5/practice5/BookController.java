package example.day5.practice5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired private BookService bookService;

    @GetMapping("/day5/book")
    public List<BookDto> printAll(){
        List<BookDto> result=bookService.printAll();
        return result;
    }

    @PostMapping("/day5/book")
    public boolean AddBook(@RequestBody BookDto bookDto){
        boolean result=bookService.AddBook(bookDto);
        return result;
    }

    @DeleteMapping("/day5/book")
    public boolean DeleteBook(@RequestParam int bookId){
        boolean result=bookService.DeleteBook(bookId);
        return result;
    }

    @PutMapping("/day5/book")
    public boolean UpdateBook(@RequestBody BookDto bookDto){
        boolean result=bookService.UpdateBook(bookDto);
        return result;
    }
}
