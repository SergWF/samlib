package my.wf.samlib.core.dataextract.matcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class StringMatcherTest {

    Matcher<String> matcher = new StringMatcherImpl();

    private String testName;
    private String data;
    private String pattern;
    private Boolean expected;

    public StringMatcherTest(String testName, String data, String pattern, boolean expected){
        this.testName = testName;
        this.data = data;
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        Date date = new Date();
        Object[][] data = new Object[][]{
                //{"Matched  same", "aaa", "aaa", true},
                 {"Matched same pattern upper", "aaa", "AAA", true}
                /*
                , {"Matched same pattern lower", "AAa", "aaa", true}
                , {"Matched same pattern contains", "qwertyuio", "wer", true}
                , {"Matched same pattern in the start", "qwertyuio", "qwe", true}
                , {"Matched same pattern in the end", "qwertyuio", "uio", true}
                , {"Not Matched", "qwertyu", "asdf", false}
                , {"Not Matched More", "ertyu", "qwertyuiop", false}
                , {"Not Matched End", "wertyu", "qwertyu", false}
                , {"Not Matched Start", "qwert", "qwertyu", false}
                , {"Not Matched Null Value", null, "qwer", false}
                , {"Not Matched Empty Value", "", "qwer", false}
                , {"Not Matched Empty Value With Space", "   ", "qwer", false}
                , {"Matched Null Pattern", "qwerty", null, true}
                */
        };
        return Arrays.asList(data);
    }

    @Test
    public void doTest(){
        Boolean actual = matcher.match(data, pattern);
        assertTrue(testName + ": expected " + expected + " but actual is " + actual, expected == actual);
    }

}