package ghtk.masterdev.mongodbdemo.Repository;

import ghtk.masterdev.mongodbdemo.Model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    // List all books
    List<Book> findAll();
    // Find book by id
    Optional<Book> findBy_id(String id);
    // Search by pubDate (between)
    List<Book> findByPubDateBetween(Date startDate, Date endDate);
    // Save book
    Book save(Book book);
    // Delete book
    void deleteBy_id(String id);
}
