package my.wf.samlib.core.dataextract.matcher;

/**
 * Created by Serg on 22.11.2014.
 */
public interface Matcher<T> {
    boolean match(T value, T pattern);
}
