package my.wf.samlib.core.filtering;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.ComparableItem;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class FilterItem<T extends BaseEntity> implements ComparableItem<T> {
    private String fieldName;
    private String pattern;
    private Class<T> filteredClass;
    private Customer customer;

    public FilterItem(String fieldName, String pattern, Class<T> filteredClass, Customer customer) {
        this.fieldName = fieldName;
        this.pattern = pattern;
        this.filteredClass = filteredClass;
        this.customer = customer;
    }

    public String getPattern() {
        return pattern;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public Class<T> getProcessedClass() {
        return filteredClass;
    }

    @Override
    public Customer getCustomer() {
        return customer;
    }
}
