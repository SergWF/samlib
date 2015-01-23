package my.wf.samlib.core.storage;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface AuthorStorage extends Storage<Author> {
    Author findByLink(String authorLink) throws StorageException;
}
