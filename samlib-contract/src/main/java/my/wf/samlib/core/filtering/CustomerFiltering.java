package my.wf.samlib.core.filtering;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerFiltering<T extends BaseEntity> {
    private List<FilterItem<T>> filterItems = new LinkedList<FilterItem<T>>();
    private Class<T> filteredClass;
    private Customer customer;

    public CustomerFiltering(Class<T> filteredClass, Customer customer) {
        this.filteredClass = filteredClass;
        this.customer = customer;
    }

    public CustomerFiltering<T> add(String filterName, String pattern){
        filterItems.add(new FilterItem<T>(filterName, pattern, filteredClass, customer));
        return this;
    }

    public List<FilterItem<T>> getItems(){
        return filterItems;
    }
}
