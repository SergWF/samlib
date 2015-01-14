package my.wf.samlib.storage.json.model;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class WritingJson extends Writing {

    @Override
    @JsonBackReference
    public Author getAuthor() {
        return super.getAuthor();
    }

    @Override
    @JsonIgnore
    public Boolean unreadByCustomer(Customer customer) {
        return super.unreadByCustomer(customer);
    }

}
