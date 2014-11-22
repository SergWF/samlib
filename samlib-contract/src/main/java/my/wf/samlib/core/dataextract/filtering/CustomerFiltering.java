package my.wf.samlib.core.dataextract.filtering;

import my.wf.samlib.core.dataextract.DataExtractor;
import my.wf.samlib.core.dataextract.matcher.MatcherFactory;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerFiltering<T extends BaseEntity> {
    private List<FilterItem> filterItems = new LinkedList<FilterItem>();
    private Class<T> filteredClass;
    private Customer customer;
    private DataExtractor<T> dataExtractor;
    private MatcherFactory matcherFactory;

    public CustomerFiltering(Class<T> filteredClass, Customer customer, DataExtractor<T> dataExtractor, MatcherFactory matcherFactory) {
        this.filteredClass = filteredClass;
        this.customer = customer;
        this.dataExtractor = dataExtractor;
        this.matcherFactory = matcherFactory;
    }

    public <K> CustomerFiltering<T> add(String filterName, K pattern){
        filterItems.add(new FilterItem(filterName, pattern, customer, pattern.getClass()));
        return this;
    }

    public List<FilterItem> getItems(){
        return filterItems;
    }

    public boolean matched(T entity){
        for(FilterItem item: getItems()){
            Object pattern = item.getFilterValue();
            Object value = dataExtractor.getValue(entity, item);
            if(!matcherFactory.getMatcher(item.getFieldValueClass()).match(value, pattern)){
                return false;
            }
        }
        return true;
    }
}
