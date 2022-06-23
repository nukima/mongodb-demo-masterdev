package ghtk.masterdev.mongodbdemo.Controller;

import ghtk.masterdev.mongodbdemo.Model.Book;
import ghtk.masterdev.mongodbdemo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

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

    //Find book by pubDate (between), pattern: yyyy-MM-dd
    @GetMapping("/books/datebetween")
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

    //Save all books
    @PostMapping("/books/saveall")
    public List<Book> saveAllBooks(@RequestBody List<Book> books) {
        return bookService.saveAll(books);
    }
    //Partial update
    @PatchMapping("/books/{id}")
    public void partialUpdate(@PathVariable String id, @RequestBody Book book) throws Exception {
        for(Field filed : book.getClass().getDeclaredFields()) {
            String fieldName = filed.getName();
            if(fieldName.equals("_id")) {
                continue;
            }
            Method getter = Book.class.getDeclaredMethod("get" + StringUtils.capitalize(fieldName));
            Object fieldValue = getter.invoke(book);
            if(Objects.nonNull(fieldValue)) {
                bookService.partialUpdate(id, fieldName, fieldValue);
            }
        }
    }

    // Delete book
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBy_id(id);
    }
}
