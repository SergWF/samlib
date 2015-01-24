package my.wf.samlib.service.controller;

import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.requestprocessor.SecurityRequestProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Serg on 23.01.2015.
 */

@Controller
public class AuthController {
    private Customer customer;
    private int loginTries;

    @Autowired
    SecurityRequestProcessor securityRequestProcessor;

    public void login(String userName, String password){
        this.customer = securityRequestProcessor.getCustomerByCredentials(userName, password);
        if(null == customer){
            loginTries++;
        }else{
            loginTries = 0;
        }
    }

    public int getLoginTries(){
        return loginTries;
    }

    public void logout(){
        this.customer = null;
    }

    public Customer getActiveCustomer(){
        return customer;
    }
}
