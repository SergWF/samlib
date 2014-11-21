package my.wf.samlib.core.helper;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.model.extender.HasLink;

import java.util.Collection;

/**
 * Created by Serg on 21.11.2014.
 */
public class SearchHelper {
    public static <T extends HasLink> T searchByLink(String link, Collection<T> source){
        if(null == link || 0 == link.trim().length()){
            return null;
        }
        for(T entity: source){
            if(link.equals(entity.getLink())){
                return entity;
            }
        }
        return null;
    }
}
