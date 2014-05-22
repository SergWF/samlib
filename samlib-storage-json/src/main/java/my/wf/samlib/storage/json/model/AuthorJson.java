package my.wf.samlib.storage.json.model;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import java.util.Date;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorJson extends Author {
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    @JsonManagedReference
    public Set<Writing> getWritings() {
        return super.getWritings();
    }

    @Override
    public void setWritings(Set<Writing> writings) {
        super.setWritings(writings);
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
}
