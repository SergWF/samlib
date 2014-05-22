package my.wf.samlib.core.message.exception;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class AuthorNotFoundException extends CustomerException {
    private String link;
    private Long id;

    public AuthorNotFoundException(Customer customer, String authorLink) {
        super(customer);
        this.link = authorLink;
    }

    public AuthorNotFoundException(Customer customer, Long authorId) {
        super(customer);
        this.id = authorId;
    }

    public String getAuthorLink() {
        return link;
    }

    public Long getAuthorId() {
        return id;
    }
}
