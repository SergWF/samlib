package my.wf.samlib.storage.file.storage;

import my.wf.samlib.core.filtering.CustomerFiltering;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.core.ordering.CustomerOrdering;
import my.wf.samlib.core.storage.Storage;
import my.wf.samlib.storage.file.storage.filtering.MemoryDataFilter;
import my.wf.samlib.storage.file.storage.ordering.MemoryOrder;

import java.util.List;

/**
 * Created by Serg on 27.04.2014.
 */
public abstract class BaseStorageFileImpl<T extends BaseEntity> implements Storage<T> {
    private String dataFileName;
    private DataFieldReader dataFieldReader;
    private MemoryDataFilter dataFilter;
    private MemoryOrder dataSorter;
    private FileStorage  fileStorage;


    protected abstract EntityData<T> getData();

    public String getDataFileName() {
        return dataFileName;
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public DataFieldReader getDataFieldReader() {
        return dataFieldReader;
    }

    public void setDataFieldReader(DataFieldReader dataFieldReader) {
        this.dataFieldReader = dataFieldReader;
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

    public FileStorage getFileStorage() {
        return fileStorage;
    }

    public void setFileStorage(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @Override
    public T get(Long id) {
        return getData().getById(id);
    }

    @Override
    public T save(T entity) {
        entity = getData().save(entity);
        getFileStorage().save(getData(), getDataFileName());
        return entity;
    }

    @Override
    public T remove(T entity) {
        return getData().remove(entity);
    }

    @Override
    public List<T> list(CustomerFiltering<T> filter, CustomerOrdering<T> order) {
        return getDataSorter().sort(getDataFilter().doFilter(getData().getData(), filter), order);
    }
}
