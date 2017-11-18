package ge.chalauri.sdata;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.util.resources.LocaleData;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Chalauri-G on 11/3/2017.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    //findBy is prefix and spring will generate sql according to rest name;
    Book findByTitle(String title);

    //findBy is prefix and spring will generate sql according to rest name;
    // in parameter we should pass '%' accordingly.
    // for example : %of% , %of , of%
    List<Book> findByTitleLike(String title);

    List<Book> findByTitleContaining(String title);

    List<Book> findByTitleStartingWith(String title);

    List<Book> findByTitleEndingWith(String title);

    List<Book> findByTitleIgnoreCase(String title);

    //----

    // In this case field type has to implement comparable interface
    List<Book> findByPriceEquals(BigDecimal price);

    List<Book> findByPriceGreaterThan(BigDecimal price);

    List<Book> findByPriceGreaterThanEqual(BigDecimal price);

    List<Book> findByPriceLessThan(BigDecimal price);

    List<Book> findByPriceLessThanEqual(BigDecimal price);

    List<Book> findByPriceBetween(BigDecimal lower, BigDecimal upper);

    //------
    List<Book> findByTitleContainingOrTitleContaining(String titleOne, String titleSecond);

    List<Book> findByTitleContainingAndPriceGreaterThan(String title, BigDecimal price);

    List<Book> findByTitleNot(String title);

    //------
    List<Book> findByPublishAfter(LocaleData date);

    List<Book> findByPublishBefore(LocaleData date);

    List<Book> findByPublishBetween(LocaleData lower, LocaleData upper);

    //------
    // ORDERING

    List<Book> findByTitleContainingOrderByTitleAsc(String title);

    List<Book> findByTitleContainingOrderByTitleDesc(String title);


    //-------
    // LIMITING

    List<Book> findTopByOrderByPriceDesc(); //It will retrieve all but get first

    List<Book> findTop5ByOrderByPriceDesc(); //It will retrieve all but get first 5

    List<Book> findTop5ByTitleOrderByPriceDesc();

    List<Book> findFirstByOrderByPriceAsc(); //It will retrieve all but get first

    //-------
    // Transversing nested properties (JOINS)

    List<Book> findByAuthorFirstName(String firstName);

    // more explicit for spring data that we are looking firstName in author entity
    List<Book> findByAuthor_FirstName(String firstName);


    //--------

    @Query("SELECT b FROM Book b")
    List<Book> queryOne();

    @Query("SELECT b FROM Book b WHERE b.price >= ?1 ")
    List<Book> queryTwo(BigDecimal price);

    @Query("SELECT b FROM Book b WHERE b.title = :title ")
    List<Book> queryThree(@Param("title") String title);

    //---------
    // NAMED QUERIES

    List<Book> nqueryOne();

    List<Book> nqueryTwo(BigDecimal price);

    List<Book> nqueryThree(@Param("title") String title);

    //-------

    List<Book> findByPriceGreaterThan(BigDecimal price, Pageable pageable);

    //-------
    /*
    our implementation, for that our interface extends BookRepositoryCustom interface
    in configuration we should apply repository-impl-postfix property (in xml)
     */
    // here should not be method, it is just derived from BookRepositoryCustom

    //----------
    // UPDATING


    @Transactional
    @Modifying
    @Query("UPDATE Book b set b.price = ?2 where b.title LIKE  ?1")
    int setPrice(String title, BigDecimal price);
}
