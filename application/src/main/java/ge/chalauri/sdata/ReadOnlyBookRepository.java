package ge.chalauri.sdata;

import org.springframework.stereotype.Repository;

/**
 * Created by Chalauri-G on 11/4/2017.
 */
@Repository
public interface ReadOnlyBookRepository extends ReadOnlyRepository<Book, Long> {
}
