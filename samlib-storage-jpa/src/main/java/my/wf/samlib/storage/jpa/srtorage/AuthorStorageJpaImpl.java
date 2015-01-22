package my.wf.samlib.storage.jpa.srtorage;

import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.storage.jpa.model.AuthorJpa;
import my.wf.samlib.storage.jpa.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Serg on 22.01.2015.
 */
@Component
public class AuthorStorageJpaImpl implements AuthorStorage {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author findByLink(String authorLink) throws StorageException {
        return authorRepository.findByLink(authorLink);
    }

    @Override
    public Author get(Long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public Author save(Author entity) throws StorageException {
        return authorRepository.save((AuthorJpa) entity);
    }

    @Override
    public Author remove(Author entity) throws StorageException {
        authorRepository.delete((AuthorJpa) entity);
        return entity;
    }

    @Override
    public List<Author> list() {
        return (List<Author>)(List<?>)authorRepository.findAll();
    }
}
