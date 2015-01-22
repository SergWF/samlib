package my.wf.samlib.core.model.comparator;

import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.model.entity.Author;

import java.util.Comparator;

/**
 * Created by Serg on 21.01.2015.
 */
public class AuthorComparator implements Comparator<Author> {
    private CustomerOrdering ordering;

    public AuthorComparator(CustomerOrdering ordering) {
        this.ordering = ordering;
    }

    @Override
    public int compare(Author o1, Author o2) {
        return 0;
    }
}
