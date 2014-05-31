package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.dataextract.filtering.CustomerFiltering;
import my.wf.samlib.core.dataextract.ordering.CustomerOrdering;
import my.wf.samlib.core.factory.impl.DefaultFilterFactory;
import my.wf.samlib.core.factory.impl.DefaultOrderFactory;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.storage.json.filtering.MemoryDataFilter;
import my.wf.samlib.storage.json.model.AuthorJson;
import my.wf.samlib.storage.json.ordering.MemoryOrder;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class BaseStorageJsonImplTest {


    Date updateDate = getDate(2010, 10, 10, 10, 10, 10);
    Date beforeUpdateDate = getDate(2010, 10, 10, 9, 9, 9);
    Date afterUpdateDate = getDate(2010, 10, 10, 20, 20, 20);
    BaseStorageJsonImpl<Author> storage;
    EntityStorage entityStorage;
    AuthorJson author1;
    AuthorJson author2;
    AuthorJson author3;
    AuthorJson author4;
    CustomerFiltering<Author> filter;
    CustomerOrdering<Author> order;




    @Before
    public void setUp() throws IOException {

        filter = new DefaultFilterFactory().createFilter(Author.class, new Customer());
        order = new DefaultOrderFactory().createOrdering(Author.class, new Customer());

        entityStorage = Mockito.mock(EntityStorage.class);
        storage = Mockito.spy(new BaseStorageJsonImpl<Author>() {
            @Override
            protected Class<Author> getStoredClass() {
                return Author.class;
            }
        });
        storage.setDataSorter(new MemoryOrder());
        storage.setDataFilter(new MemoryDataFilter());
        storage.getDataFilter().setDataExtractorFactory(new DataExtractorFactory());
        storage.getDataSorter().setDataExtractorFactory(new DataExtractorFactory());
        entityStorage.setMapper(new ObjectMapper());
        author1 = EntityJsonCreator.createAuthor(1L);
        author2 = EntityJsonCreator.createAuthor(2L);
        author3 = EntityJsonCreator.createAuthor(3L);
        author4 = EntityJsonCreator.createAuthor(4L);

        storage.setEntityStorage(entityStorage);
        Mockito.doReturn(new SamlibData()).when(entityStorage).getData();
        Mockito.doReturn(updateDate).when(entityStorage).getUpdateDate();
        entityStorage.getData().getAuthors().add(author1);
        entityStorage.getData().getAuthors().add(author2);
        entityStorage.getData().getAuthors().add(author3);
        entityStorage.getData().getAuthors().add(author4);
    }

    //If data was refreshed, LastUpdateDate has to be changed
    @Test
    public void testRefreshSetLastChangeDateChanged() throws Exception {
        storage.setLastRefreshDate(updateDate);
        storage.refresh(afterUpdateDate);
        assertEquals("Last Update Date has to be changed to " + afterUpdateDate, afterUpdateDate, storage.getLastRefreshDate());
    }

    //If data was not refreshed, LastUpdateDate has not to be changed
    @Test
    public void testRefreshSetLastChangeDateNotChanged() throws Exception {
        storage.setLastRefreshDate(updateDate);
        storage.refresh(beforeUpdateDate);
        assertEquals("Last Update Date has not to be changed to " + beforeUpdateDate, updateDate, storage.getLastRefreshDate());
    }

    //If EntityStorage.updateDate < lastRefreshDate no real refreshing calling
    @Test
    public void testDoNotRefresh() throws Exception {
        storage.refresh(beforeUpdateDate);
        Mockito.verify(storage, Mockito.never()).internalRefresh(Mockito.any(Date.class));
    }

    //If EntityStorage.updateDate >= lastRefreshDate do real refreshing
    @Test
    public void testDoRefresh() throws Exception {
        Mockito.doReturn(new SamlibData()).when(entityStorage).getData();
        storage.refresh(afterUpdateDate);
        Mockito.verify(storage, Mockito.atLeastOnce()).internalRefresh(afterUpdateDate);
    }

    //If EntityStorage.updateDate is null (o refreshes before) do real refreshing
    @Test
    public void testDoRefreshFirstTime() throws Exception {
        Mockito.doReturn(null).when(entityStorage).getUpdateDate();
        storage.refresh(afterUpdateDate);
        Mockito.verify(storage, Mockito.atLeastOnce()).internalRefresh(afterUpdateDate);
    }

    @Test
    public void testGetOk() throws Exception {
        Author expected = author2;
        Author actual = storage.get(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void testGetFault() throws Exception {
        assertNull(storage.get(100L));
    }

    @Test
    public void testSave() throws Exception {
        Author expected = new AuthorJson();
        Mockito.doReturn(1L).when(entityStorage).generateId();
        Author actual = storage.save(expected);
        assertNotNull("ID is NULL", actual.getId());
        assertTrue("Id  is less or equals 0", actual.getId() > 0);
        assertTrue("Author is not found in the cache map", storage.getEntityCollection().contains(actual));
        Mockito.verify(entityStorage, Mockito.times(1)).saveData();
        Mockito.verify(storage, Mockito.times(1)).get(actual.getId());
    }

    @Test
    public void testRemove() throws Exception {
        Author expected = author3;
        Author actual = storage.remove(expected);
        assertEquals("Author is not same", expected, actual);
        Mockito.verify(storage, Mockito.never()).refresh(Mockito.any(Date.class));
        Mockito.verify(entityStorage, Mockito.times(1)).saveData();
        Set<Author> authors = storage.getEntityCollection();
        assertTrue("Size is wrong: " + authors.size(), authors.size() == 3);
        assertTrue("Author1 not found", authors.contains(author1));
        assertTrue("Author2 not found", authors.contains(author2));
        assertFalse("Author3 found (must be deleted)", authors.contains(author3));
        assertTrue("Author4 not found", authors.contains(author4));
    }

    @Test
    public void testList() throws Exception {
        List<Author> list = storage.list(filter, order);
        assertTrue("Size is wrong: " + list.size(), list.size()==4);
        assertTrue("Author1 not found", list.contains(author1));
        assertTrue("Author2 not found", list.contains(author2));
        assertTrue("Author3 not found", list.contains(author3));
        assertTrue("Author4 not found", list.contains(author4));
    }

    private Date getDate(int year, int month, int day, int hours, int minutes, int seconds){
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month -1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);
        return cal.getTime();
    }

}
