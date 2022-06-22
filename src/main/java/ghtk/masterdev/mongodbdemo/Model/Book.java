package ghtk.masterdev.mongodbdemo.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "book")
@Data
public class Book {
    @Id
    private String _id;

    @TextIndexed
    private String name;

    @TextIndexed
    private String author;

    private Date pubDate;

    private String description;


}
