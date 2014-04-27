package my.wf.samlib.core.requestprocessor;

import my.wf.samlib.core.message.ErrorMessage;
import my.wf.samlib.core.message.InfoMessage;
import my.wf.samlib.core.message.SamlibMessage;
import my.wf.samlib.core.message.WarningMessage;
import my.wf.samlib.core.message.exception.SamlibException;
import my.wf.samlib.core.model.entity.Customer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class MessageProcessor {
    private List<SamlibMessage> messages = new LinkedList<SamlibMessage>();

    public SamlibMessage addMessage(SamlibMessage message) {
        messages.add(message);
        return message;
    }

    public List<SamlibMessage> getMessages(Integer length) {
        List<SamlibMessage> list = messages.subList(messages.size() - length, messages.size());
        Collections.reverse(list);
        return list;
    }

    public SamlibMessage addInfoMessage(Customer customer, String text) {
        return addInfoMessage(customer, text, null);
    }

    public SamlibMessage addInfoMessage(Customer customer, String text, String[] info) {
        return addMessage(new InfoMessage(customer, text, info));
    }

    public SamlibMessage addWarnMessage(Customer customer, String text) {
        return addWarnMessage(customer, text, null);
    }

    public SamlibMessage addWarnMessage(Customer customer, String text, String[] info) {
        return addMessage(new WarningMessage(customer, text, info));
    }

    public SamlibMessage addErrorMessage(Customer customer, String text, SamlibException exception) {
        return addErrorMessage(customer, text, null);
    }

    public SamlibMessage addErrorMessage(Customer customer, String text, String[] info, SamlibException exception) {
        return addMessage(new ErrorMessage(customer, text, info, exception));
    }


}
