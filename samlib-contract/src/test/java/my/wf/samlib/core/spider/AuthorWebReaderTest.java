package my.wf.samlib.core.spider;

import my.wf.samlib.core.EntityCreator;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.sprider.AuthorWebReader;
import org.junit.Before;

import java.lang.reflect.Method;

/**
 * Created by Serg on 20.11.2014.
 */
public class AuthorWebReaderTest {

    AuthorWebReader authorWebReader;
    Writing oldWriting = EntityCreator.createWriting(100L);
    Writing newWriting = new Writing();

    @Before
    public void setUp(){
        newWriting.setLink(oldWriting.getLink());
        newWriting.setDescription(oldWriting.getDescription());
        newWriting.setSize(oldWriting.getSize());
    }

    public void checkChangesTest() throws NoSuchMethodException {

    }


}
