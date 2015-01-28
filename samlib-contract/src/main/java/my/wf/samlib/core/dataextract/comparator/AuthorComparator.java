package my.wf.samlib.core.dataextract.comparator;

import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created by Serg on 28.01.2015.
 */
public class AuthorComparator extends EntityComparator<Author> {
    private Customer customer;

    public AuthorComparator(CustomerOrdering ordering, Customer customer) {
        super(ordering);
        this.customer = customer;
    }

    @Override
    protected Comparable getFieldValue(String fieldName, Author author) {
        switch (fieldName){
            case "id": return author.getId();
            case "name": return author.getName();
            case "link": return author.getLink();
            case "lastChangedDate": return author.getLastChangedDate();
            case "unreadWritingsCount": return author.getUneadWritngsCount(customer);
            default: throw new IllegalArgumentException("Authors can not be compared by ["+fieldName+"] filed");
        }
    }
}
