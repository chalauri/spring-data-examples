package ge.chalauri.sdata.mongo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by Chalauri-G on 11/11/2017.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        MongoOperations template = context.getBean(MongoTemplate.class);

        BookDocument bookDocument = new BookDocument();

        //save
        template.save(bookDocument);

        //save to specific collection
        template.save(bookDocument, "collectionName");

        //Querying & update
        BasicQuery basicQuery = new BasicQuery("{title:'brave new book'}");
        BookDocument document = template.find(basicQuery, BookDocument.class).get(0);
        document.setTitle("new title");
        template.save(document);

        // Updating
        Query query = query(where("title").is("title value"));
        Update update = update("title", "updatedValue");
        template.updateFirst(query, update, BookDocument.class); //-- this approach updates only first book
        template.updateMulti(query, update, BookDocument.class); //-- this approach updates all books

        // Upsert
        Query query1 = query(where("title").is("title value"));
        Update update1 = update("price", new BigDecimal(100));
        template.upsert(query1, update1, BookDocument.class); // If query found data it will be updated otherwise inserted

        // Criteria & Query -- > REGEX
        Criteria criteria = Criteria.where("title").regex(Pattern.compile(".*of.*")); // everything that contains of
        Query query2 = new Query(criteria);

        List<BookDocument> bookDocuments = template.find(query2, BookDocument.class);


        // MONGO REPOSITORY

        BookMongoRepository repository = context.getBean(BookMongoRepository.class);

        repository.save(new BookDocument());

        //GEOSPATIAL FEATURES
        Point boston = new Point(-71.061973, 42.356940);
        Distance distance = new Distance(200, Metrics.MILES);
        List<BookDocument> listByDistance = repository.findByLocationNear(boston, distance);


        // FULL TEXT SEARCH
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching("Dickens");
        List<BookDocument> fullTextSearchResult = repository.findAllBy(textCriteria);
        List<BookDocument> fullTextSearchResultOrdered = repository.findAllByOrderByScoreDesc(textCriteria);


        // JSON QUERIES
        List<BookDocument> expensiveBooks = repository.findExpensiveBooks(new BigDecimal(120));

    }
}
