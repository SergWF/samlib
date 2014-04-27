package my.wf.samlib.storage.file.storage.filtering;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.filtering.FilterItem;
import my.wf.samlib.core.model.extender.Filterable;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class MemoryDataFilter {
    public <T extends Filterable> List<T> doFilter(Collection<T> list, CustomerFiltering<T> filtering){
        List<T> result = new LinkedList<T>();
        for(T entity: list){
            if(filterMatched(entity, filtering)){
                result.add(entity);
            }
        }
        return result;
    }

    private <T extends Filterable> boolean filterMatched(T entity, CustomerFiltering<T> filtering) {
        boolean res = true;
        for(FilterItem<T> item: filtering.getItems()){
            if(!getValue(entity, item).contains(item.getPattern())){
                return false;
            }
        }
        return res;
    }

    private <T extends Filterable> String getValue(T entity, FilterItem<T> item){

    }
}
