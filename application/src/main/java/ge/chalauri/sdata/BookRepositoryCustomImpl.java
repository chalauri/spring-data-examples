package ge.chalauri.sdata;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created by Chalauri-G on 11/5/2017.
 */

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    Logger logger = Logger.getLogger(BookRepositoryCustomImpl.class);

    @Autowired
    private EntityManager em;

    @Override
    @Transactional
    public void saveAndLog(Book book) {
        logger.debug("Save method");
        em.persist(book);
    }
}
