package my.wf.samlib.core.storage;

import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface AuthorStorage extends Storage<Author> {
    Author findByLink(String authorLink) throws StorageException;
    List<Writing> getAuthorsWritings(Author author, CustomerFiltering<Writing> filter, CustomerOrdering<Writing> order);
    List<Author> getCustomerList(Customer customer);
}
