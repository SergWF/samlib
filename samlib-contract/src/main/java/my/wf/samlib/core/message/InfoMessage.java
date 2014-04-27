package my.wf.samlib.core.message;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class InfoMessage extends SamlibMessage {

    public InfoMessage(Customer customer, String text) {
        super(customer, text, null);
    }

    public InfoMessage(Customer customer, String text, String[] info) {
        super(customer, text, info);
    }

    @Override
    public Level getLevel() {
        return Level.INFO;
    }

}
