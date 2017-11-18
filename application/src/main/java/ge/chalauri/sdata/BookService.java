package ge.chalauri.sdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Chalauri-G on 11/3/2017.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private ReadOnlyBookRepository readOnlyRepository;

    public void save(Book book) {
        this.repository.save(book);
    }


    public void delete(List<Long> ids) {
        this.repository.delete(repository.findAll(ids)); // makes as many queries as ids' size is
    }

    public void deleteInBatch(List<Long> ids) {
        this.repository.deleteInBatch(repository.findAll(ids)); // makes only one sql statement
    }

    public Page<Book> findAllWithPageable() {
        // First argument is page number (zero based) and second argument is rows on page
        return this.repository.findAll(new PageRequest(0, 10));
    }

    public List<Book> findByPriceGreaterThan(BigDecimal price) {
        return this.repository.findByPriceGreaterThan(price, new PageRequest(0, 10));
    }

    public List<Book> findAllSortedByTitle() {
        //by default sorting is ascending
        return this.repository.findAll(new Sort("title"));
    }

    public List<Book> findAllSortedByTitleDesc() {
        return this.repository.findAll(new Sort(Sort.Direction.DESC, "title"));
    }

    public List<Book> sortWithDifferentFields() {
        return this.repository.findAll(new Sort(Sort.Direction.ASC, "author.firstName", "price"));
    }

    public List<Book> sortWithDifferentFieldsAndDifferentDirection() {
        return this.repository.findAll(
                new Sort(Sort.Direction.ASC, "author.firstName")
                .and(new Sort(Sort.Direction.DESC, "price")));
    }

    public Page<Book> getPageableBooks(){
        return this.repository.findAll(new PageRequest(0,20));
    }
}
