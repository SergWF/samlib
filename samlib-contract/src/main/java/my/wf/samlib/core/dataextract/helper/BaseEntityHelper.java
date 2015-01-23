package my.wf.samlib.core.dataextract.helper;

import my.wf.samlib.core.model.entity.BaseEntity;

/**
 * Created by Serg on 21.01.2015.
 */
public class BaseEntityHelper {
    public static int getEntityHash(BaseEntity entity){
        return (null != entity.getId())?entity.getId().hashCode():((Object)entity).hashCode();
    }

    public static boolean getEntityEquals(BaseEntity entity, Object other){
        if (entity == other) return true;
        if (!(other instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) other;
        if(entity.getId() != null){
            return entity.getId().equals(that.getId());
        }else{
            return entity == other;
        }

    }
}
