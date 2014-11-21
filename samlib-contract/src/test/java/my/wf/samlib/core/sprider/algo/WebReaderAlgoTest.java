package my.wf.samlib.core.sprider.algo;

import my.wf.samlib.core.EntityCreator;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Writing;
import my.wf.samlib.core.sprider.AuthorWebReader;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class WebReaderAlgoTest {

    Writing oldWriting = EntityCreator.createWriting(100L);
    Writing newWriting = new Writing();

    @Before
    public void setUp(){
        newWriting.setLink(oldWriting.getLink());
        newWriting.setDescription(oldWriting.getDescription());
        newWriting.setSize(oldWriting.getSize());
    }

    @Test
    public void testGetCharset() throws Exception {
        assertEquals("utf-8", WebReaderAlgo.getCharset("text/html; charset=utf-8", AuthorWebReader.DEFAULT_ENCODING));
    }
    @Test
    public void testGetCharsetDefault() throws Exception {
        assertEquals(AuthorWebReader.DEFAULT_ENCODING, WebReaderAlgo.getCharset("text/html;", AuthorWebReader.DEFAULT_ENCODING));
    }
    @Test
    public void testGetCharsetDefaultForEmpty() throws Exception {
        assertEquals(AuthorWebReader.DEFAULT_ENCODING, WebReaderAlgo.getCharset("", AuthorWebReader.DEFAULT_ENCODING));
    }
    @Test
    public void testGetCharsetDefaultForNull() throws Exception {
        assertEquals(AuthorWebReader.DEFAULT_ENCODING, WebReaderAlgo.getCharset(null, AuthorWebReader.DEFAULT_ENCODING));
    }

    @Test
    public void testRefreshAuthor() throws Exception {
        Author author = new Author();
        Author newAuthor = new Author();
        Date readDate = new Date();
        WebReaderAlgo.refreshAuthor(author, newAuthor, readDate);
    }

    @Test
    public void testCheckChangesSame() throws Exception {
        assertFalse(WebReaderAlgo.checkChanges(oldWriting, newWriting));
    }

    @Test
    public void testCheckChangesSize() throws Exception {
        newWriting.setSize("1K");
        assertTrue(WebReaderAlgo.checkChanges(oldWriting, newWriting));
    }

    @Test
    public void testCheckChangesDescr() throws Exception {
        newWriting.setDescription("Changed Description " + new Date().getTime());
        assertTrue(WebReaderAlgo.checkChanges(oldWriting, newWriting));
    }

}