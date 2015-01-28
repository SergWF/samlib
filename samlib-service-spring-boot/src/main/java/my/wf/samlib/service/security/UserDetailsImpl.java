package my.wf.samlib.service.security;

import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.service.controller.Anonymous;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Serg on 25.01.2015.
 */
public class UserDetailsImpl implements UserDetails {
    private Customer customer;

    public UserDetailsImpl(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            public String getAuthority() {
                if(Anonymous.NAME.equals(customer.getUserName())){
                    return "ROLE_ANONYMOUS";
                }else {
                    return "ROLE_USER";
                }
            }
        };
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return customer.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
