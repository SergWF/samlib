package my.wf.samlib.storage.json.model;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorJson extends Author {
    //private Set<Long> writingIds;

    @Override
    @JsonIgnore
    public Set<Writing> getWritings() {
        return super.getWritings();
    }

    @Override
    @JsonIgnore
    public Date getLastChangedDate() {
        return super.getLastChangedDate();
    }

    @Override
    @JsonIgnore
    public Boolean unreadByCustomer(Customer customer) {
        return super.unreadByCustomer(customer);
    }

/*
    public Set<Long> getWritingIds() {
        Set<Long> ids = new HashSet<Long>();
        for (Writing writing : getWritings()) {
            ids.add(writing.getId());
        }
        return ids;
    }

    public void setWritingIds(Set<Long> writingIds) {
        this.writingIds = writingIds;
    }
    */
}
