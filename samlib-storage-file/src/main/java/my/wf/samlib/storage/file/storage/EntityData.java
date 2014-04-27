package my.wf.samlib.storage.file.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.BaseEntity;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class EntityData<T extends BaseEntity> {
    private Map<Long, T> entities = new HashMap<Long, T>();

    public T getById(Long id){
        return entities.get(id);
    }

    public synchronized T save(T entity) {
        if(null == entity.getId()){
            entity.setId(getFreeId());
        }
        entities.put(entity.getId(), entity);
        return entity;
    }

    private Long getFreeId() {
        return Collections.max(entities.keySet()) + 1;
    }

    public Collection<T> getData(){
        return entities.values();
    }

    public T remove(T entity) {
        entities.remove(entity.getId());
        return entity;
    }
}
