package my.wf.samlib.core.dataextract.matcher;

/**
 * Created by Serg on 22.11.2014.
 */
public class MatcherImpl<T> implements Matcher<T> {

    @Override
    public boolean match(T value, T pattern) {
        if(null == pattern){
            return true;
        }
        return (null != value) && value.equals(pattern);
    }
}
