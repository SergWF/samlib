package my.wf.samlib.storage.json.model;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class WritingJson extends Writing {
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getLink() {
        return super.getLink();
    }

    @Override
    public void setLink(String link) {
        super.setLink(link);
    }

    @Override
    @JsonBackReference
    public Author getAuthor() {
        return super.getAuthor();
    }

    @Override
    public void setAuthor(Author author) {
        super.setAuthor(author);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public String getSize() {
        return super.getSize();
    }

    @Override
    public void setSize(String size) {
        super.setSize(size);
    }

    @Override
    public Date getLastChangedDate() {
        return super.getLastChangedDate();
    }

    @Override
    public void setLastChangedDate(Date lastChangedDate) {
        super.setLastChangedDate(lastChangedDate);
    }

    @Override
    public String getGroupName() {
        return super.getGroupName();
    }

    @Override
    public void setGroupName(String groupName) {
        super.setGroupName(groupName);
    }

    @Override
    @JsonIgnore
    public Boolean unreadByCustomer(Customer customer) {
        return super.unreadByCustomer(customer);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
}
