package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.BaseEntity;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class EntityData<T extends BaseEntity> {
    private Map<Long, T> entities = new HashMap<Long, T>();
    private Class<T> entityClass;
    private Date saveDate;


    public EntityData(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public T getById(Long id){
        return entities.get(id);
    }

    public synchronized T save(T entity) {
        if(null == entity.getId()){
            entity.setId(getFreeId());
        }
        entities.put(entity.getId(), entity);
        setSaveDate(new Date());
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
