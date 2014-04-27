package my.wf.samlib.core.message;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class WarningMessage extends SamlibMessage {

    public WarningMessage(Customer customer, String text) {
        super(customer, text, null);
    }

    public WarningMessage(Customer customer, String text, String[] info) {
        super(customer, text, info);
    }

    @Override
    public Level getLevel() {
        return Level.WARN;
    }
}
