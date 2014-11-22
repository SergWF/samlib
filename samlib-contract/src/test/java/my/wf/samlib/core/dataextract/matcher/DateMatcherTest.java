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
public class DateMatcherTest {

    Matcher<Date> matcher = new MatcherImpl<Date>();
    private String testName;
    private Date date;
    private Date pattern;
    private Boolean expected;

    public DateMatcherTest(String testName, Date date, Date pattern, boolean expected){
        this.testName = testName;
        this.date = date;
        this.pattern = pattern;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws ParseException {
        Date date = new Date();
        Object[][] data = new Object[][]{
                {"Matched  same", date, new Date(date.getTime()), true}
                , {"Matched Less", date, new Date(date.getTime() - 1000), false}
                , {"Matched More", date, new Date(date.getTime() + 1000), false}
                , {"Matched Null Value", null, date, false}
                , {"Matched Null Pattern", date, null, true}
        };
        return Arrays.asList(data);
    }

    @Test
    public void doTest(){
        Boolean actual = matcher.match(date, pattern);
        assertTrue(testName + ": expected " + expected + " but actual is " + actual, expected == actual);
    }
}