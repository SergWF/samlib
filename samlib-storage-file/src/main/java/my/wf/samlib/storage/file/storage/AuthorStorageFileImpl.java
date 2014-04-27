package my.wf.samlib.storage.file.storage;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.storage.AuthorStorage;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorStorageFileImpl extends BaseStorageFileImpl<Author> implements AuthorStorage {

    private EntityData<Author> authorData;

    @Override
    public EntityData getData() {
        if(null == authorData){
            authorData = new EntityData<Author>();
        }
        return authorData;
    }

    @Override
    public Author findByLink(String authorLink) {
        if(null == authorLink || 0 == authorLink.trim().length()){
            return null;
        }
        for(Author author: authorData.getData()){
            if(author.getLink().equals(authorLink)){
                return author;
            }
        }
        return null;
    }

    @Override
    public List<Writing> getAuthorsWritings(Author author, CustomerFiltering<Writing> filter, CustomerOrdering<Writing> order) {
        return getDataSorter().sort(getDataFilter().doFilter(author.getWritings(), filter), order);
    }
}
