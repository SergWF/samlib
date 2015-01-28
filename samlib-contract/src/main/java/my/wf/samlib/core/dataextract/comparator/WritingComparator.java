package my.wf.samlib.core.dataextract.comparator;

import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

/**
 * Created by Serg on 28.01.2015.
 */
public class WritingComparator extends EntityComparator<Writing> {
    private Customer customer;

    public WritingComparator(CustomerOrdering ordering, Customer customer) {
        super(ordering);
        this.customer = customer;
    }

    @Override
    protected Comparable getFieldValue(String fieldName, Writing writing) {
        switch (fieldName){
            case "id": return writing.getId();
            case "name": return writing.getName();
            case "link": return writing.getLink();
            case "lastChangedDate": return writing.getLastChangedDate();
            case "unread": return writing.unreadByCustomer(customer);
            case "size": return writing.getSize();
            case "group": return writing.getGroupName();
            default: throw new IllegalArgumentException("Authors can not be compared by ["+fieldName+"] filed");
        }
    }

    @Override
    protected int defaultCompare(Writing o1, Writing o2) {
        int res = o1.getLastChangedDate().compareTo(o2.getLastChangedDate());
        return 0 != res?res: super.defaultCompare(o1, o2);

    }
}
