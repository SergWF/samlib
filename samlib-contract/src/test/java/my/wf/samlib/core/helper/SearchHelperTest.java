package my.wf.samlib.core.helper;

import my.wf.samlib.core.model.extender.HasLink;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class SearchHelperTest {

    private final Set<HasLink> sources = new HashSet<HasLink>();
    private static final String linkOK ="link1";
    private static final String linkNotFound ="linkNotFound";


    @Before
    public void setUp(){
        sources.add(new HasLink() {@Override public String getLink() { return linkOK + "1";}});
        sources.add(new HasLink() {@Override public String getLink() { return linkOK + "2";}});
        sources.add(new HasLink() {@Override public String getLink() { return linkOK + "3";}});
        sources.add(new HasLink() {@Override public String getLink() { return linkOK + "4";}});
        sources.add(new HasLink() {@Override public String getLink() { return linkOK;}});
        sources.add(new HasLink() {@Override public String getLink() { return linkOK + "5";}});
        sources.add(new HasLink() {@Override public String getLink() { return linkOK + "6";}});
        sources.add(new HasLink() {@Override public String getLink() { return linkOK + "7";}});
    }

    @Test
    public void testSearchOK() throws Exception {
        HasLink entity = SearchHelper.searchByLink(linkOK, sources);
        assertNotNull(entity);
        assertEquals(linkOK, entity.getLink());
    }

    @Test
    public void testSearchNotFound() throws Exception {
        assertNull(SearchHelper.searchByLink(linkNotFound, sources));
    }

    @Test
    public void testSearchNull() throws Exception {
        assertNull(SearchHelper.searchByLink(null, sources));
    }
}