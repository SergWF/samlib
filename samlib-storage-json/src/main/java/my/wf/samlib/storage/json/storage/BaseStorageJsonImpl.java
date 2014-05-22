package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.storage.Storage;
import my.wf.samlib.storage.json.filtering.MemoryDataFilter;
import my.wf.samlib.storage.json.ordering.MemoryOrder;

import java.util.*;

/**
 * Created by Serg on 27.04.2014.
 */
public abstract class BaseStorageJsonImpl<T extends BaseEntity> implements Storage<T> {
    private MemoryDataFilter dataFilter;
    private MemoryOrder dataSorter;
    private EntityStorage entityStorage;
    private Map<Long, T> cachedData = new HashMap<Long, T>();
    private Date lastRefreshDate;

    protected BaseStorageJsonImpl() {
    }


    protected abstract Class<T> getStoredClass();

    public EntityStorage getEntityStorage() {
        return entityStorage;
    }

    public void setEntityStorage(EntityStorage entityStorage) {
        this.entityStorage = entityStorage;
    }

    public MemoryDataFilter getDataFilter() {
        return dataFilter;
    }

    public void setDataFilter(MemoryDataFilter dataFilter) {
        this.dataFilter = dataFilter;
    }

    public MemoryOrder getDataSorter() {
        return dataSorter;
    }

    public void setDataSorter(MemoryOrder dataSorter) {
        this.dataSorter = dataSorter;
    }

    protected Set<T> getEntityCollection(){
        return (Set<T>) checkedGetEntityStorage().getData().getData(getStoredClass());
    }

    protected Date getLastRefreshDate() {
        return lastRefreshDate;
    }

    protected void setLastRefreshDate(Date lastRefreshDate) {
        this.lastRefreshDate = lastRefreshDate;
    }

    protected Map<Long, T> getCachedData() {
        return cachedData;
    }

    protected void setCachedData(Map<Long, T> cachedData) {
        this.cachedData = cachedData;
    }

    protected void refresh(Date lastRefreshDate){
        if(!getLastRefreshDate().before(getEntityStorage().getUpdateDate())) {
            return;
        }
        internalRefresh(lastRefreshDate);
    }

    protected void internalRefresh(Date lastRefreshDate){
        setLastRefreshDate(lastRefreshDate);
        cachedData.clear();
        for (T entity : getEntityCollection()) {
            cachedData.put(entity.getId(), entity);
        }
    }

    protected EntityStorage checkedGetEntityStorage() {
        if(null == entityStorage){
            throw new IllegalStateException("No EntityStorage object is present");
        }
        return entityStorage;
    }

    @Override
    public T get(Long id) {
        refresh(new Date());
        return cachedData.get(id);
    }

    @Override
    public T save(T entity) throws StorageException {
        if(null == entity.getId()){
            entity.setId(checkedGetEntityStorage().generateId(getStoredClass()));
        }
        getEntityCollection().add(entity);
        refresh(new Date());
        return get(entity.getId());
    }

    @Override
    public T remove(T entity) {
        getEntityCollection().remove(entity);
        refresh(new Date());
        return entity;
    }

    @Override
    public List<T> list(CustomerFiltering<T> filter, CustomerOrdering<T> order) {
        refresh(new Date());
        List<T> list = new ArrayList<T>(getEntityCollection());
        return getDataSorter().sort(getDataFilter().doFilter(list, filter), order);
    }
}
