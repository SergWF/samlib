package my.wf.samlib.core.helper;

import my.wf.samlib.core.model.extender.LastChanged;

import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class LastChangedDateHelper {
    public static Date getLastChanged(Collection<? extends LastChanged> collection){
        if(null == collection || collection.isEmpty()){
            return null;
        }
        Date date = collection.iterator().next().getLastChangedDate();
        for(LastChanged entity:collection){
            if(date.before(entity.getLastChangedDate())){
                date = entity.getLastChangedDate();
            }
        }
        return date;
    }
}
