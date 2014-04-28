package my.wf.samlib.core.filtering;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class FilterItem<K> implements ComparableItem<K> {
    private String fieldName;
    private String pattern;
    //private Class<T> filteredClass;
    private Customer customer;
    private Class<K> fieldValueClass;

    public FilterItem(String fieldName, String pattern, /*Class<T> filteredClass, */Customer customer, Class<K> fieldValueClass) {
        this.fieldName = fieldName;
        this.pattern = pattern;
        //this.filteredClass = filteredClass;
        this.customer = customer;
        this.fieldValueClass = fieldValueClass;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

//    @Override
//    public Class<T> getProcessedClass() {
//        return filteredClass;
//    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    @Override
    public Class getFieldClassValue() {
        return null;
    }
}
