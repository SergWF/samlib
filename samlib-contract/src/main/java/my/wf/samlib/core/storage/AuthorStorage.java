package my.wf.samlib.core.storage;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface AuthorStorage extends Storage<Author> {
    Author findByLink(String authorLink) throws StorageException;
    Writing getWriting(Long writingId);
}
