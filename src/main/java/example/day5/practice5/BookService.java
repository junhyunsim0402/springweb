package example.day5.practice5;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired BookRepository bookRepository;

    public List<BookDto> printAll(){
        List<BookEntity> bookEntitiyList=bookRepository.findAll();
        List<BookDto> bookDtoList=new ArrayList<>();
        bookEntitiyList.forEach(entitiy->{
            BookDto bookDto=new BookDto();
            bookDto.setBookId(entitiy.getBookId());
            bookDto.setBookName(entitiy.getBookName());
            bookDto.setWriter(entitiy.getWriter());
            bookDto.setPublisher(entitiy.getPublisher());
            bookDtoList.add(bookDto);
        });
        return bookDtoList;
    }

    public boolean AddBook(BookDto bookDto){
        BookEntity bookEntitiy=new BookEntity();
        bookEntitiy.setBookName(bookDto.getBookName());
        bookEntitiy.setWriter(bookDto.getWriter());
        bookEntitiy.setPublisher(bookDto.getPublisher());
        BookEntity saveEntity=bookRepository.save(bookEntitiy);
        if(saveEntity.getBookId()>=1)return true;
        return false;
    }

    public boolean DeleteBook(int bookId){
        bookRepository.deleteById(bookId);
        return true;
    }

    @Transactional
    public boolean UpdateBook(BookDto bookDto){
        Optional<BookEntity> optional=
                bookRepository.findById(bookDto.getBookId());
        if(optional.isPresent()){
            BookEntity bookEntitiy=optional.get();
            bookEntitiy.setBookName(bookDto.getBookName());
            bookEntitiy.setWriter(bookDto.getWriter());
            bookEntitiy.setPublisher(bookDto.getPublisher());
            return true;
        }return false;
    }
}
