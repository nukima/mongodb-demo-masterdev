package ghtk.masterdev.mongodbdemo.Controller;

import ghtk.masterdev.mongodbdemo.Model.Book;
import ghtk.masterdev.mongodbdemo.Repository.BookRepository;
import ghtk.masterdev.mongodbdemo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    // List all books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    // Find book by id
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookService.findById(id).orElse(null);
    }

    //Find book by pubDate (between)
    @GetMapping("/books/getdate")
    public List<Book> getBookBetweenDate(@RequestParam(value = "start") String start, @RequestParam(value = "end") String end){
        return bookService.findByPubDateBetween(start, end);
    }

    // fulltext search
    @GetMapping("/books/search")
    public List<Book> getBookByNameOrAuthor(@RequestParam(value = "q") String searchPhrase) {
        return bookService.findByNameOrAuthor(searchPhrase);
    }

    // Create book
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    // Delete book
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBy_id(id);
    }


}
