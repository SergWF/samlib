package my.wf.samlib.core.dataextract.filtering;

import my.wf.samlib.core.model.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class CustomerFiltering {
    private List<FilterItem> filterItems = new ArrayList<>();

    public CustomerFiltering add(String filterName, String pattern){
        filterItems.add(new FilterItem(filterName, pattern));
        return this;
    }

    public List<FilterItem> getItems(){
        return filterItems;
    }

}
