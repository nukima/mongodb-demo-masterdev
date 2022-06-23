package ghtk.masterdev.mongodbdemo.Service;

import ghtk.masterdev.mongodbdemo.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface BookService{
    // List all books (pageable)
    Page<Book> findAll(Pageable pageable);
    // Find book by id
    Optional<Book> findById(String id);
    // Fulltext search
    List<Book> findByNameOrAuthor(String searchPhrase);
    // Search by pubDate (between)
    List<Book> findByPubDateBetween(String startDate, String endDate);
    // Save book
    Book save(Book book);
    //saveAll
    List<Book> saveAll(List<Book> books);
    // Delete book
    void deleteBy_id(String id);
    //partial update
    void partialUpdate(String id, String fieldName, Object fieldValue);
}
