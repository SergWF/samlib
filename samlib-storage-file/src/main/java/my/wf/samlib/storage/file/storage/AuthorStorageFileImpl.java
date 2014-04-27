package my.wf.samlib.storage.file.storage;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorStorageFileImpl implements AuthorStorage {

    private String dataFileName;
    private AuthorData authorData;


    public String getDataFileName() {
        return dataFileName;
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    @Override
    public Author findByLink(String authorLink) {
        return authorData.getByLink(authorLink);
    }

    @Override
    public List<Writing> getAuthorsWritings(Author author, CustomerFiltering<Writing> filter, CustomerOrdering<Writing> order) {
        return null;
    }

    @Override
    public Author get(Long id) {
        return null;
    }

    @Override
    public Author save(Author entity) {
        return null;
    }

    @Override
    public Author remove(Author entity) {
        return null;
    }

    @Override
    public List<Author> list(CustomerFiltering<Author> filter, CustomerOrdering<Author> order) {
        return null;
    }
}
