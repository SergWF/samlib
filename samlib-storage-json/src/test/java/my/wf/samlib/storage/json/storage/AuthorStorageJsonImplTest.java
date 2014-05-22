package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.dataextract.DataExtractorFactory;
import my.wf.samlib.core.factory.AuthorFactory;
import my.wf.samlib.core.factory.CustomerFactory;
import my.wf.samlib.core.factory.FilterFactory;
import my.wf.samlib.core.factory.OrderFactory;
import my.wf.samlib.core.factory.impl.DefaultFilterFactory;
import my.wf.samlib.core.factory.impl.DefaultOrderFactory;
import my.wf.samlib.core.message.exception.ExtractFieldDataException;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.storage.json.factory.AuthorJsonFactoryImpl;
import my.wf.samlib.storage.json.factory.CustomerFactoryJsonImpl;
import my.wf.samlib.storage.json.filtering.MemoryDataFilter;
import my.wf.samlib.storage.json.model.AuthorJson;
import my.wf.samlib.storage.json.model.WritingJson;
import my.wf.samlib.storage.json.ordering.MemoryOrder;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertTrue;

public class AuthorStorageJsonImplTest {

    AuthorStorageJsonImpl storage = new AuthorStorageJsonImpl();


    /*
    private static final Long updateDate = 1400070000000L;

    private static final String json="{\"entityClass\":\"my.wf.samlib.core.model.entity.Author\",\"saveDate\":\"+updateDate+\",\"data\":[{\"id\":1,\"name\":\"author_1\",\"writings\":[{\"id\":100002,\"name\":\"writing_100002\",\"link\":\"http://writinglink_100002\",\"description\":\"description_100002\",\"groupName\":\"group_100002\",\"size\":\"2k\",\"lastChangedDate\":\"+updateDate+\"},{\"id\":100000,\"name\":\"writing_100000\",\"link\":\"http://writinglink_100000\",\"description\":\"description_100000\",\"groupName\":\"group_100000\",\"size\":\"0k\",\"lastChangedDate\":\"+updateDate+\"},{\"id\":100001,\"name\":\"writing_100001\",\"link\":\"http://writinglink_100001\",\"description\":\"description_100001\",\"groupName\":\"group_100001\",\"size\":\"1k\",\"lastChangedDate\":\"+updateDate+\"}],\"link\":\"http://link_1\"},{\"id\":2,\"name\":\"author_2\",\"writings\":[{\"id\":200001,\"name\":\"writing_200001\",\"link\":\"http://writinglink_200001\",\"description\":\"description_200001\",\"groupName\":\"group_200001\",\"size\":\"1k\",\"lastChangedDate\":\"+updateDate+\"},{\"id\":200000,\"name\":\"writing_200000\",\"link\":\"http://writinglink_200000\",\"description\":\"description_200000\",\"groupName\":\"group_200000\",\"size\":\"0k\",\"lastChangedDate\":\"+updateDate+\"},{\"id\":200002,\"name\":\"writing_200002\",\"link\":\"http://writinglink_200002\",\"description\":\"description_200002\",\"groupName\":\"group_200002\",\"size\":\"2k\",\"lastChangedDate\":\"+updateDate+\"}],\"link\":\"http://link_2\"},{\"id\":3,\"name\":\"author_3\",\"writings\":[{\"id\":300000,\"name\":\"writing_300000\",\"link\":\"http://writinglink_300000\",\"description\":\"description_300000\",\"groupName\":\"group_300000\",\"size\":\"0k\",\"lastChangedDate\":\"+updateDate+\"},{\"id\":300001,\"name\":\"writing_300001\",\"link\":\"http://writinglink_300001\",\"description\":\"description_300001\",\"groupName\":\"group_300001\",\"size\":\"1k\",\"lastChangedDate\":\"+updateDate+\"},{\"id\":300002,\"name\":\"writing_300002\",\"link\":\"http://writinglink_300002\",\"description\":\"description_300002\",\"groupName\":\"group_300002\",\"size\":\"2k\",\"lastChangedDate\":\"+updateDate+\"}],\"link\":\"http://link_3\"}]}";

    AuthorStorageJsonImpl storage;
    Writer writer;
    FilterFactory filterFactory = new DefaultFilterFactory();
    OrderFactory orderFactory = new DefaultOrderFactory();
    Customer customer = new Customer();
    EntityStorage entityStorage = Mockito.mock(EntityStorage.class);

    @Before
    public void setUp() throws StorageException, IOException {
        storage = Mockito.spy(new AuthorStorageJsonImpl());
        storage.setEntityStorage(entityStorage);
        storage.setDataFilter(new MemoryDataFilter());
        storage.setDataSorter(new MemoryOrder());
        storage.getDataSorter().setDataExtractorFactory(new DataExtractorFactory());
    }
    @Test
    public void testSaveAuthor() throws Exception {
        storage.save(createAuthor(1L));
        storage.save(createAuthor(2L));
        storage.save(createAuthor(3L));

    }

    @Test
    public void testReadAuthorList() throws ExtractFieldDataException, StorageException {
        storage.setData(null);
        List<Author> list = storage.list(filterFactory.createFilter(Author.class, customer), orderFactory.createOrdering(Author.class, customer));
        assertTrue("Size is not same", list.size() == 3);
        for(int i = 0; i< list.size(); i++){
            assertTrue("Element is not equal "+i+": ("+list.get(i).getId()+")", list.get(i).getId() == i + 1L);
        }
    }

    private Author createAuthor(Long id) {
        Author author = new AuthorJson();
        author.setId(id);
        author.setLink("http://link_" + id);
        author.setName("author_" + id);
        while(author.getWritings().size() < 3){
            author.getWritings().add(createWriting(author));
        }
        return author;
    }

    private Writing createWriting(Author author) {
        Integer wCount = author.getWritings().size();
        Long id = author.getId() * 100000 + wCount;
        Writing writing = new WritingJson();
        writing.setId(id);
        writing.setAuthor(author);
        writing.setLink("http://writinglink_"+id);
        writing.setName("writing_"+id);
        writing.setSize(wCount+"k");
        writing.setGroupName("group_" + id);
        writing.setDescription("description_" + id);
        writing.setLastChangedDate(new Date());
        return writing;
    }
    */
}