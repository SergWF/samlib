package my.wf.samlib.core.message;

import my.wf.samlib.core.model.entity.Customer;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public abstract class SamlibMessage {
    public static enum Level {
        INFO, WARN, ERROR
    }

    private Customer customer;
    private Date date;
    private String text;
    private String[] info;

    protected SamlibMessage(Customer customer, String text, String[] info) {
        this.customer = customer;
        this.date = new Date();
        this.text = text;
        this.info = info;
    }

    public abstract Level getLevel();

    public Date getDate() {
        return date;
    }

    String getText() {
        return text;
    }

    public String[] getInfo() {
        return info;
    }

}
