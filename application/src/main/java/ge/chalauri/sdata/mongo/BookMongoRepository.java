package ge.chalauri.sdata.mongo;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Chalauri-G on 11/14/2017.
 */
public interface BookMongoRepository extends MongoRepository<BookDocument, String>{

    BookDocument findByTitle(String title);

    List<BookDocument> findByLocationNear(Point p, Distance d);

    List<BookDocument> findAllBy(TextCriteria criteria);
    List<BookDocument> findAllByOrderByScoreDesc(TextCriteria criteria);


    @Query("{'price':{$gt:?0}}")
    List<BookDocument> findExpensiveBooks(BigDecimal price);
}
