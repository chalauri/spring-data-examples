package ge.chalauri.sdata;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

/**
 * Created by Chalauri-G on 11/4/2017.
 */

/*
This interface (repository) is created in order not to expose all
methods from spring data repositories hierarchy
*/

@NoRepositoryBean // this prevents spring data to pick this interface and build repository
public interface ReadOnlyRepository<T, ID extends Serializable> extends Repository<T,ID>{

    T findOne(ID id);
    Iterable<T> findAll();
}
