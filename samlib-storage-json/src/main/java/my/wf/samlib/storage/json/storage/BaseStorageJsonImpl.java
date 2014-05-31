package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.storage.Storage;
import my.wf.samlib.storage.json.exception.JsonStorageException;
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
    private Map<Long, T> cachedData;
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
        if(null ==  cachedData){
            cachedData = new HashMap<Long, T>();
        }
        return cachedData;
    }

    protected void setCachedData(Map<Long, T> cachedData) {
        this.cachedData = cachedData;
    }

    protected void refresh(Date lastRefreshDate){
        if(null == getEntityStorage().getUpdateDate() || !lastRefreshDate.before(getEntityStorage().getUpdateDate())) {
            internalRefresh(lastRefreshDate);
        }
    }

    protected void internalRefresh(Date lastRefreshDate){
        setLastRefreshDate(lastRefreshDate);
        getCachedData().clear();
        for (T entity : getEntityCollection()) {
            getCachedData().put(entity.getId(), entity);
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
        return getCachedData().get(id);
    }

    @Override
    public T save(T entity) throws StorageException {
        if(null == entity.getId()){
            entity.setId(checkedGetEntityStorage().generateId());
        }
        getEntityCollection().add(entity);
        getEntityStorage().saveData();
        return get(entity.getId());
    }

    @Override
    public T remove(T entity) throws StorageException {
        getEntityCollection().remove(entity);
        getEntityStorage().saveData();
        return entity;
    }

    @Override
    public List<T> list(CustomerFiltering<T> filter, CustomerOrdering<T> order) {
        refresh(new Date());
        List<T> list = new ArrayList<T>(getEntityCollection());
        return getDataSorter().sort(getDataFilter().doFilter(list, filter), order);
    }
}
