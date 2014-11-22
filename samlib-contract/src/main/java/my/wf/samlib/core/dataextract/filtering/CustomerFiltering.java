package my.wf.samlib.core.dataextract.filtering;

import my.wf.samlib.core.dataextract.DataExtractor;
import my.wf.samlib.core.dataextract.matcher.Matcher;
import my.wf.samlib.core.dataextract.matcher.MatcherImpl;
import my.wf.samlib.core.dataextract.matcher.StringMatcherImpl;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.entity.Customer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerFiltering<T extends BaseEntity> {
    private List<FilterItem> filterItems = new LinkedList<FilterItem>();
    private Class<T> filteredClass;
    private Customer customer;
    private DataExtractor<T> dataExtractor;

    private static final Map<Class, Matcher> matcherMap = new HashMap<Class, Matcher>();
    static{
        matcherMap.put(String.class, new StringMatcherImpl());
    }


    public CustomerFiltering(Class<T> filteredClass, Customer customer, DataExtractor<T> dataExtractor) {
        this.filteredClass = filteredClass;
        this.customer = customer;
        this.dataExtractor = dataExtractor;
    }

    public <K> CustomerFiltering<T> add(String filterName, K pattern){
        filterItems.add(new FilterItem(filterName, pattern, customer, pattern.getClass()));
        return this;
    }

    public List<FilterItem> getItems(){
        return filterItems;
    }

    private <T> Matcher<T> getMatcher(Class<T> clazz){
        Matcher<T> matcher = matcherMap.get(clazz);
        return (null != matcher) ? matcher : new MatcherImpl<T>();
    }

    public boolean matched(T entity){
        for(FilterItem item: getItems()){
            Object pattern = item.getFilterValue();
            Object value = dataExtractor.getValue(entity, item);
            if(!getMatcher(item.getFieldValueClass()).match(value, pattern)){
                return false;
            }
        }
        return true;
    }
}
