package my.wf.samlib.service.controller;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.requestprocessor.SecurityRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.Set;

/**
 * Created by Serg on 23.01.2015.
 */

@Controller
public class AuthController {
    //private Customer anonymous = new Anonymous();
    private Customer customer;
    private int loginTries;

    @Autowired
    SecurityRequestProcessor securityRequestProcessor;

    @PreAuthorize("isAnonymous()")
    public void login(String userName, String password){
        this.customer = securityRequestProcessor.getCustomerByCredentials(userName, password);
        if(null == customer){
            loginTries++;
        }else{
            loginTries = 0;
        }
    }

    @PreAuthorize("isAnonymous()")
    public int getLoginTries(){
        return loginTries;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void logout(){
        this.customer = null;
    }

    @PreAuthorize("isAnonymous()")
    public Customer getActiveCustomer(){
        return customer;
        //return null == customer?anonymous:customer;
    }
}
