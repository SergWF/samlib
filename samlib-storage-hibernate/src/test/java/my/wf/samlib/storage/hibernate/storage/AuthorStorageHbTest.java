package my.wf.samlib.storage.hibernate.storage;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AuthorStorageHbTest extends BaseStorageTest {

    private AuthorStorageHb storage;
    private Customer customer;
    private Author author;

    @Before
    public void setUp() throws StorageException {
        super.setUp();
        storage = new AuthorStorageHb(sessionFactory);
        author = authors.get(1);
        customer = customers.get(0);
    }

    @Test
    public void testMapping() {
        storage.get(-1L);
    }

    @Test
    public void testGet() throws StorageException {
        Long expectedId = author.getId();
        Author actual = storage.get(expectedId);
        assertNotNull(actual);
        assertEquals(expectedId, actual.getId());
    }

    @Test
    public void testGetNull(){
        assertNull(storage.get(-1L));
    }

    @Test
    public void testFindByLink() throws Exception {
        String link = author.getLink();
        Author actual = storage.findByLink(link);
        assertEquals(author, actual);
    }

    @Test
    public void testGetAuthorsWritingsNoFilterNoOrder() throws Exception {
        List<Writing> authorsWritings = storage.getAuthorsWritings(author, null, null);
        assertEquals(author.getWritings().size(), authorsWritings.size());
        for(Writing w: authorsWritings){
            System.out.println(w.getLink());
        }

    }

    @Test
    public void testGetCustomerList() throws Exception {
        //storage.getCustomerList(customer);
    }

    @Test
    public void testSave() throws StorageException {
        Author a = EntityHbCreator.createAuthorFull(9L, 3);
        a = storage.save(a);
        Author saved = storage.get(a.getId());
        assertNotNull(saved.getId());
        assertEquals(3, saved.getWritings().size());
        for(Writing w: saved.getWritings()){
            assertNotNull(w.getId());
        }
    }
}