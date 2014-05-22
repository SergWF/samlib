package my.wf.samlib.core.dataextract.filtering;

import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class FilterItem<K> implements ComparableItem<K> {
    private String fieldName;
    private K filterValue;
    private Customer customer;
    private Class<K> fieldValueClass;

    public FilterItem(String fieldName, K filterValue, Customer customer, Class<K> fieldValueClass) {
        this.fieldName = fieldName;
        this.filterValue = filterValue;
        this.customer = customer;
        this.fieldValueClass = fieldValueClass;
    }

    public K getFilterValue() {
        return filterValue;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }

    public Class getFieldValueClass() {
        return fieldValueClass;
    }
}
