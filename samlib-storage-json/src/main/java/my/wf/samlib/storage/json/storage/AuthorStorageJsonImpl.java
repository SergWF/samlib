package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.storage.json.model.AuthorJson;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorStorageJsonImpl extends BaseStorageJsonImpl<Author> implements AuthorStorage {

    public AuthorStorageJsonImpl() {
    }

    @Override
    protected Class<Author> getStoredClass() {
        return Author.class;
    }

    @Override
    public Author findByLink(String authorLink) throws StorageException {
        if(null == authorLink || 0 == authorLink.trim().length()){
            return null;
        }
        for(AuthorJson author: checkedGetEntityStorage().getData().getAuthors()){
            if(author.getLink().equals(authorLink)){
                return author;
            }
        }
        return null;
    }

    @Override
    public Writing getWriting(Long writingId) {
        return null;
    }

//    @Override
//    public List<Writing> getAuthorsWritings(Author author, CustomerFiltering<Writing> filter, CustomerOrdering<Writing> order)  {
//            return getDataSorter().sort(getDataFilter().doFilter(get(author.getId()).getWritings(), filter), order);
//    }

    @Override
    public List<Author> list() {
        return null;
    }
}
