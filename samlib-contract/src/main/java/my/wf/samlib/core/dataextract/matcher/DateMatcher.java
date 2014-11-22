package my.wf.samlib.core.dataextract.matcher;

import java.util.Date;

public class DateMatcher implements Matcher<Date> {
    @Override
    public boolean match(Date value, Date pattern) {
        if(null == pattern){
            return true;
        }
        return (null != value) && value.equals(pattern);
    }
}
