package my.wf.samlib.core.message.exception;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorNotFoundException extends CustomerException {
    private String link;

    public AuthorNotFoundException(Customer customer, String link) {
        super(customer);
        this.link = link;
    }

    public AuthorNotFoundException(Throwable cause, Customer customer, String link) {
        super(cause, customer);
        this.link = link;
    }
}
