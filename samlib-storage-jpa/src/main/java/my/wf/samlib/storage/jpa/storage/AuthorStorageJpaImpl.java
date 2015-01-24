package my.wf.samlib.storage.jpa.storage;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.storage.jpa.model.AuthorJpa;
import my.wf.samlib.storage.jpa.repository.AuthorRepository;
import my.wf.samlib.storage.jpa.repository.WritingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Serg on 22.01.2015.
 */
@Component
public class AuthorStorageJpaImpl implements AuthorStorage {
    private static final Logger logger = LoggerFactory.getLogger(AuthorStorageJpaImpl.class);

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    WritingRepository writingRepository;

    @Override
    public Author findByLink(String authorLink) throws StorageException {
        logger.debug("Search author by link ["+authorLink+"]");
        return authorRepository.findByLink(authorLink);
    }

    @Override
    public Writing getWriting(Long writingId) {
        logger.debug("Get writing by ID ["+writingId+"]");
        return writingRepository.getOne(writingId);
    }

    @Override
    public Author get(Long id) {
        logger.debug("Get author by ID ["+id+"]");
        return authorRepository.getOne(id);
    }

    @Override
    public Author save(Author entity) throws StorageException {
        logger.debug("Save Author " + entity.getName());
        return authorRepository.save((AuthorJpa) entity);
    }

    @Override
    public Author remove(Author entity) throws StorageException {
        logger.debug("Remove Author " + entity.getName());
        authorRepository.delete((AuthorJpa) entity);
        return entity;
    }

    @Override
    public List<Author> list() {
        logger.debug("Get Author list");
        return (List<Author>)(List<?>)authorRepository.findAll();
    }
}
