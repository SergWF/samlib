package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.storage.json.model.AuthorJson;
import my.wf.samlib.storage.json.model.CustomerJson;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.*;

public class EntityStorageTest {

    private static final String JSON_STRING = "";
    EntityStorage entityStorage;
    AuthorJson author1;
    AuthorJson author2;
    AuthorJson author3;
    CustomerJson  customer1;

    @Before
    public void setUp() throws Exception {
        entityStorage = Mockito.spy(new EntityStorage());
        author1 = EntityJsonCreator.createAuthorWithWritings(1L, 2);
        author2 = EntityJsonCreator.createAuthorWithWritings(2L, 1);
        author3 = EntityJsonCreator.createAuthorWithWritings(3L, 3);
        customer1 = EntityJsonCreator.createCustomer(author1.getWritings().iterator().next(), author3.getWritings().iterator().next());
        customer1.getAuthors().add(author1);
        customer1.getAuthors().add(author2);
        customer1.getAuthors().add(author3);
    }

    @Test
    public void testGetDataLazyRead() throws Exception {
        //Must call readData when no data was read before
        Mockito.doReturn(new SamlibData()).when(entityStorage).readData(Mockito.any(Reader.class));
        Mockito.doReturn(new StringReader(JSON_STRING)).when(entityStorage).getReader();
        entityStorage.setData(null);
        entityStorage.getData();
        Mockito.verify(entityStorage, Mockito.times(1)).readData(Mockito.any(Reader.class));
    }

    @Test
    public void testGetDataNotRead() throws Exception {
        //Must not call readData when data was read before
        entityStorage.setData(new SamlibData());
        entityStorage.getData();
        Mockito.verify(entityStorage, Mockito.never()).readData(Mockito.any(Reader.class));
    }

    @Test
    public void testReadData() throws Exception {
        entityStorage.readData(new StringReader(JSON_STRING));
        throw new NotImplementedException();
    }

    @Test
    public void testWriteData() throws Exception {
        Writer writer = new StringWriter();
        Mockito.doReturn(createData()).when(entityStorage).getData();
        entityStorage.setMapper(new ObjectMapper());
        entityStorage.writeData(writer);
        System.out.println(writer.toString());
    }

    @Test
    public void testSaveData() throws Exception {
        entityStorage.saveData();
        throw new NotImplementedException();
    }

    private SamlibData createData(){
        SamlibData data = new SamlibData();
        data.sequence = 10L;
        data.authors.add(author1);
        data.authors.add(author2);
        data.authors.add(author3);
        data.customers.add(customer1);
        return data;
    }
}