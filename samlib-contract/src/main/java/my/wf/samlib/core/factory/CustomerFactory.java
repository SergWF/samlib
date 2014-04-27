package my.wf.samlib.core.factory;

import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public interface CustomerFactory  {
    Customer newCustomer(String name);
}

