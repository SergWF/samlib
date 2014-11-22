package my.wf.samlib.core.helper;

import my.wf.samlib.core.model.extender.LastChanged;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
@RunWith(Parameterized.class)
public class LastChangedDateHelperTest {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    public static final String EXPECTED = "2014.01.01";

    private class LastChangedImpl implements LastChanged{
        private Date date;

        LastChangedImpl(Date date) {
            this.date = date;
        }

        @Override
        public Date getLastChangedDate() {
            return date;
        }
    }

    private String testName;
    private String expectedStringValue;
    private Collection<String> dates;

    public LastChangedDateHelperTest(String testName, String expectedValue, Collection<String> dates) {
        this.testName = testName;
        this.expectedStringValue = expectedValue;
        this.dates = dates;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Date expected = null;
        try {
            expected = sdf.parse(EXPECTED);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Object[][] data = new Object[][]{
                {"SimpleList", EXPECTED, Arrays.asList("2013.12.01", EXPECTED, "2013.12.01","2013.12.01")}
                , {"SimpleListFirst", EXPECTED, Arrays.asList(EXPECTED, "2013.12.01", "2013.12.01","2013.12.01")}
                , {"SimpleListLast", EXPECTED, Arrays.asList("2013.12.01", "2013.12.01","2013.12.01", EXPECTED)}
                , {"SingleVal", EXPECTED, Arrays.asList(EXPECTED)}
                , {"All same", EXPECTED, Arrays.asList(EXPECTED, EXPECTED, EXPECTED, EXPECTED)}
                , {"EmptyList", null, new ArrayList<String>()}
        };
        return Arrays.asList(data);
    }
    @Test
    public void testGetLastChanged() throws Exception {
        Collection<? extends LastChanged> testCollection = getCollection(dates);
        Date expectedValue = null == expectedStringValue?null:sdf.parse(expectedStringValue);
        Date actual = LastChangedDateHelper.getLastChanged(testCollection);
        assertEquals(testName + ": expected " + (null == expectedValue?"null":sdf.format(expectedValue)) + " but actual is " + (null ==actual?"null":sdf.format(actual)), expectedValue, actual);
    }

    private Collection<LastChanged> getCollection(Collection<String> dates) throws ParseException {
        ArrayList<LastChanged> list = new ArrayList<LastChanged>(dates.size());
        for(String date: dates){
            list.add(new LastChangedImpl(sdf.parse(date)));
        }
        return list;
    }
}
