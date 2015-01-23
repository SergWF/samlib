package my.wf.samlib.core.sprider.algo;

import my.wf.samlib.core.sprider.AuthorWebReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WebReaderAlgoTest {

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

/*
    Writing oldWriting;
    Writing newWriting;
    Author oldAuthor;
    Author newAuthor;

    @Before
    public void setUp(){
        oldWriting = EntityCreator.createWriting(100L);
        newWriting = EntityCreator.copyWriting(oldWriting);
        oldAuthor = EntityCreator.createAuthor(1L);
        oldAuthor.getWritings().add(EntityCreator.createWriting(10L));
        oldAuthor.getWritings().add(EntityCreator.createWriting(20L));
        oldAuthor.getWritings().add(EntityCreator.createWriting(30L));
        oldAuthor.getWritings().add(EntityCreator.createWriting(40L));
        oldAuthor.getWritings().add(EntityCreator.createWriting(50L));

        for(Writing w: oldAuthor.getWritings()){
            w.setAuthor(oldAuthor);
        }

        newAuthor = EntityCreator.copyAuthor(oldAuthor);
    }



    @Test
    public void testRefreshAuthorNoChanges() throws Exception {
        Date lastChangedDate = oldAuthor.getLastChangedDate();
        WebReaderAlgo.refreshAuthor(oldAuthor, newAuthor);
        assertEquals(lastChangedDate, oldAuthor.getLastChangedDate());
    }

    @Test
    public void testRefreshAuthorWritingChanged() throws Exception {
        Date readDate = new Date();
        Iterator<Writing> iterator = newAuthor.getWritings().iterator();
        iterator.next();
        Writing changed = iterator.next();
        changed.setLastChangedDate(readDate);
        changed.setSize("0K");
        WebReaderAlgo.refreshAuthor(oldAuthor, newAuthor);
        assertEquals(readDate, oldAuthor.getLastChangedDate());
    }

    @Test
    public void testRefreshAuthorWritingAdded() throws Exception {
        Date readDate = new Date();
        Writing added = EntityCreator.createWriting(11L);
        added.setLastChangedDate(EntityCreator.changeDay(readDate, -1));
        newAuthor.getWritings().add(added);
        WebReaderAlgo.refreshAuthor(oldAuthor, newAuthor);
        assertEquals(newAuthor.getLastChangedDate(), oldAuthor.getLastChangedDate());
    }

    @Test
    public void testRefreshAuthorWritingRemovedNoChanged() throws Exception {
        Date readDate = new Date();
        newAuthor.getWritings().remove(1);
        Date lastChangedDate = oldAuthor.getLastChangedDate();
        WebReaderAlgo.refreshAuthor(oldAuthor, newAuthor);
        assertEquals(lastChangedDate, oldAuthor.getLastChangedDate());
    }

    @Test
    public void testCheckChangesSame() throws Exception {
        assertFalse(WebReaderAlgo.checkChanges(oldWriting, newWriting));
    }

    @Test
    public void testCheckChangesSize() throws Exception {
        newWriting.setSize(new Date().getTime() + "K");
        assertTrue(WebReaderAlgo.checkChanges(oldWriting, newWriting));
    }

    @Test
    public void testCheckChangesDescr() throws Exception {
        newWriting.setDescription("Changed Description " + new Date().getTime());
        assertTrue(WebReaderAlgo.checkChanges(oldWriting, newWriting));
    }
    */
}