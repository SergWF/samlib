package my.wf.samlib.storage.json.model;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerJson extends Customer {
    private Set<Long> authorIds;
    private Set<Long> unreadWritingIds;

    @Override
    @JsonIgnore
    public Set<Author> getAuthors() {
        return super.getAuthors();
    }

    @Override
    @JsonIgnore
    public Set<Writing> getUnreadWritings() {
        return super.getUnreadWritings();
    }

    public Set<Long> getAuthorIds(){
        Set<Long> ids = new HashSet<Long>();
        for(Author author: getAuthors()){
            ids.add(author.getId());
        }
        return ids;
    }

    public void setAuthorIds(Set<Long> authorIds){
        this.authorIds = authorIds;
    }

    public Set<Long> getUnreadWritingIds(){
        Set<Long> ids = new HashSet<Long>();
        for(Writing writing: getUnreadWritings()){
            ids.add(writing.getId());
        }
        return ids;
    }

    public void setUnreadWritingIds(Set<Long> unreadWritingIds){
        this.unreadWritingIds = unreadWritingIds;
    }
}
