package ge.chalauri.sdata;


import sun.util.resources.LocaleData;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Chalauri-G on 11/3/2017.
 */
@Entity
@Table(name = "BOOKS")
@NamedQueries({
        @NamedQuery(name = "Book.nqueryOne" , query = "SELECT b FROM Book b"),
        @NamedQuery(name = "Book.nqueryTwo" , query = "SELECT b FROM Book b WHERE b.price >= ?1"),
        @NamedQuery(name = "Book.nqueryThree" , query = "SELECT b FROM Book b WHERE b.title = :title "),
})
public class Book {

    @Id
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name="PUBLISH_DATE")
    private LocaleData publishDate;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author author;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
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
}
