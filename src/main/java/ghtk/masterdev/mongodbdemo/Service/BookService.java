package ghtk.masterdev.mongodbdemo.Service;

import ghtk.masterdev.mongodbdemo.Model.Book;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface BookService{
    List<Book> findAll();
    // Find book by id
    Optional<Book> findById(String id);
    // Fulltext search
    List<Book> findByNameOrAuthor(String searchPhrase);
    // Search by pubDate (between)
    List<Book> findByPubDateBetween(String startDate, String endDate);
    // Save book
    Book save(Book book);
    // Delete book
    void deleteBy_id(String id);
}
