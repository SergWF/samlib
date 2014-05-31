package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.Author;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SamlibDataTest {

    SamlibData data;

    @Before
    public void setUp() throws Exception {
        data = new SamlibData();
    }

    @Test
    public void testGenerateNewId() throws Exception {
        assertTrue("First I value has to be 1", 1 == data.generateNewId());
        assertTrue("Second I value has to be 2", 2 == data.generateNewId());
        assertTrue("Third I value has to be 3", 3 == data.generateNewId());
    }

}