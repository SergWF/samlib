package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.Author;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Date;

public class BaseStorageJsonImplTest {

    Date updateDate = new Date();
    BaseStorageJsonImpl<Author> storage;




    @Before
    public void setUp() {
        storage = ( BaseStorageJsonImpl<Author>)Mockito.mock(BaseStorageJsonImpl.class, Mockito.CALLS_REAL_METHODS);
        Mockito.doReturn(Author.class).when(storage).getStoredClass();
        storage.setEntityStorage(Mockito.mock(EntityStorage.class));
        Mockito.doReturn(updateDate).when(storage.getEntityStorage()).getUpdateDate();
    }

    @Test
    public void testRefresh() throws Exception {
    storage.refresh(updateDate);
    }
    @Test
    public void testDoNotRefresh() throws Exception {
        Date lastRefreshDate = new Date(updateDate.getTime() + 10000);
        storage.refresh(lastRefreshDate);
        Mockito.verify(storage, Mockito.never()).internalRefresh(lastRefreshDate);
    }

    @Test
    public void testDoRefresh() throws Exception {
        Date lastRefreshDate = new Date(updateDate.getTime() - 10000);
        storage.refresh(lastRefreshDate);
        Mockito.verify(storage, Mockito.never()).internalRefresh(lastRefreshDate);
    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testList() throws Exception {

    }
}