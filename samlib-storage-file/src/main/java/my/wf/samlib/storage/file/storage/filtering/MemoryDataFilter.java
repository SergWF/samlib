package my.wf.samlib.storage.file.storage.filtering;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.filtering.FilterItem;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.storage.file.storage.DataFieldReader;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class MemoryDataFilter {

    private DataFieldReader dataFieldReader = new DataFieldReader();

    public MemoryDataFilter(DataFieldReader dataFieldReader) {
        this.dataFieldReader = dataFieldReader;
    }

    public <T extends BaseEntity> List<T> doFilter(Collection<T> list, CustomerFiltering<T> filtering){
        List<T> result = new LinkedList<T>();
        for(T entity: list){
            if(filterMatched(entity, filtering)){
                result.add(entity);
            }
        }
        return result;
    }

    private <T extends BaseEntity> boolean filterMatched(T entity, CustomerFiltering<T> filtering) {
        boolean res = true;
        for(FilterItem<T> item: filtering.getItems()){
            if(!dataFieldReader.getValue(entity, item).contains(item.getPattern())){
                return false;
            }
        }
        return res;
    }


}
