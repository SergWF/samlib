package my.wf.samlib.core.filtering;

import my.wf.samlib.core.model.extender.Filterable;
import my.wf.samlib.core.model.entity.Customer;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class FilterItem<T extends Filterable> {
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

    public String getFieldName() {
        return fieldName;
    }

    public String getPattern() {
        return pattern;
    }

    public Class<T> getFilteredClass() {
        return filteredClass;
    }

    public Customer getCustomer() {
        return customer;
    }
}
