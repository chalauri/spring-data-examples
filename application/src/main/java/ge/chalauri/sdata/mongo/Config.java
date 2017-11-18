package ge.chalauri.sdata.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * Created by Chalauri-G on 11/12/2017.
 */

@Configuration
public class Config {

    @Bean
    public MongoDbFactory mongoDbFactory(){
        MongoClientURI uri = new MongoClientURI("mongodb://username:password@localhost/dbname");

        return new SimpleMongoDbFactory(new MongoClient(uri),"dbname");
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoDbFactory());
    }

}
