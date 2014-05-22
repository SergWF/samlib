package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.storage.json.model.AuthorJson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
@RunWith(Parameterized.class)
public class AuthorStorageJsonImplFindByLinkTest {


    private static final Long id = 10L;
    private static final String link = EntityJsonCreator.getExpectedAuthorLink(id);
    private String testName;
    private boolean searchResult;
    private List<AuthorJson> authors = new ArrayList<AuthorJson>();

    AuthorStorageJsonImpl storage = new AuthorStorageJsonImpl();
    EntityStorage entityStorage = Mockito.mock(EntityStorage.class);


    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        Object[][] data = new Object[][]{
                {"Found", true, Arrays.asList(1L, 2L, 100L, 10L, 100L, 3L)}
                , {"Not Found", false, Arrays.asList(1L, 2L, 100L, 100L, 3L)}
                , {"Not Found from empty list", false, null}
                , {"Found from single list item", true, Arrays.asList(10L)}
        };
        return Arrays.asList(data);
    }

    public AuthorStorageJsonImplFindByLinkTest(String testName, Boolean searchResult, List<Long> authorIds) {
        this.testName = testName;
        this.searchResult = searchResult;
        if(null != authorIds) {
            for (Long id : authorIds) {
                authors.add(EntityJsonCreator.createAuthor(id));
            }
        }
    }

    @Before
    public void setUp(){
        storage.setEntityStorage(entityStorage);
        Mockito.doReturn(new SamlibData()).when(entityStorage).getData();
        entityStorage.getData().getAuthors().addAll(authors);
    }


    @Test
    public void testFindByLink() throws Exception {
        Author author = storage.findByLink(link);
        if(searchResult){
            assertNotNull(testName + " Author must be found", author);
            assertTrue(testName + " Wrong author.id value expected "+ id +" actual is " + author.getId() +" for link " + link, author.getId().equals(id));
        }else{
            assertNull(testName + " Author must not be found. Found author.id="+((null == author)?"null":author.getId()), author);
        }
    }
}
