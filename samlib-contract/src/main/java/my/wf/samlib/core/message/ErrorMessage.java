package my.wf.samlib.core.message;

import my.wf.samlib.core.message.exception.SamlibException;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class ErrorMessage extends SamlibMessage {
    private SamlibException exception;

    public ErrorMessage(Customer customer, String text, SamlibException exception) {
        super(customer, text, null);
        this.exception = exception;
    }

    public ErrorMessage(Customer customer, String text, String[] info, SamlibException exception) {
        super(customer, text, info);
        this.exception = exception;
    }

    @Override
    public Level getLevel() {
        return Level.ERROR;
    }
}
