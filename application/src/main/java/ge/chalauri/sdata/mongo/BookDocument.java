package ge.chalauri.sdata.mongo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ge.chalauri.sdata.Author;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TextScore;
import sun.util.resources.LocaleData;

import java.math.BigDecimal;

/**
 * Created by Chalauri-G on 11/11/2017.
 */
@Document(collection = "book")
public class BookDocument {

    @Id
    private String bookId;

    @TextIndexed(weight=1) //for text fields for full text search
    @Field(value = "title", order = 1) // order which order will have in document
    private String title;

    private BigDecimal price;

    private LocaleData publishDate;

    private Author author;

    @JsonDeserialize(using = PointDeserializer.class)
    private Point location;

    @TextScore
    private Float score;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public LocaleData getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocaleData publishDate) {
        this.publishDate = publishDate;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Float getScore() {
        return score;
    }
}
