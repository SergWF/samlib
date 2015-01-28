package my.wf.samlib.service.security;

import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.requestprocessor.CredentialsChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by Serg on 23.01.2015.
 */
@Component
public class CredentialsCheckerImpl extends CredentialsChecker {
    @Autowired
    public AuthenticationManager authenticationManager;

    //@Autowired    private UserDetailsManager manager;

    @Override
    public boolean check(Customer customer, String password) {
        UserDetailsImpl userDetails = new UserDetailsImpl(customer);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());;
        authenticationManager.authenticate(authentication);
        return super.check(customer, password);
    }
}
