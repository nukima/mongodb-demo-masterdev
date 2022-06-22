package ghtk.masterdev.mongodbdemo.Service.Impl;

import ghtk.masterdev.mongodbdemo.Model.Book;
import ghtk.masterdev.mongodbdemo.Repository.BookRepository;
import ghtk.masterdev.mongodbdemo.Service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private MongoTemplate mongoTemplate;
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findBy_id(id);
    }

    @Override
    public List<Book> findByNameOrAuthor(String searchPhrase) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchPhrase);
        Query query = TextQuery.queryText(criteria);
        return mongoTemplate.find(query, Book.class);
    }

    // Search by pubDate (between), parse String "yyyy-MM-dd" to Date
    @Override
    public List<Book> findByPubDateBetween(String startDate, String endDate) {
        try{
            Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            return bookRepository.findByPubDateBetween(start, end);
        }
        catch (ParseException e){
            e.printStackTrace();
            throw new RuntimeException("Parse date error");
        }
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBy_id(String id) {
        bookRepository.deleteById(id);
    }

}