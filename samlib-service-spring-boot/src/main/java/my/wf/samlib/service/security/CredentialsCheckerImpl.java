package my.wf.samlib.service.security;

import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.requestprocessor.CredentialsChecker;
import org.springframework.stereotype.Component;

/**
 * Created by Serg on 23.01.2015.
 */
@Component
public class CredentialsCheckerImpl extends CredentialsChecker {

    @Override
    public boolean check(Customer customer, String password) {
        return super.check(customer, password);
    }
}
