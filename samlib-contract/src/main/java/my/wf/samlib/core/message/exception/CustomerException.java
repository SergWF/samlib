package my.wf.samlib.core.message.exception;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerException extends SamlibException {
    private Customer customer;

    public CustomerException(Customer customer) {
        this.customer = customer;
    }

    public CustomerException(Throwable cause, Customer customer) {
        super(cause);
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
