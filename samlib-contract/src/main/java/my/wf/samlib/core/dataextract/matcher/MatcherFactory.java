package my.wf.samlib.core.dataextract.matcher;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MatcherFactory {
    private static final Map<Class, Matcher> matcherMap = new HashMap<>();
    static {
        matcherMap.put(String.class, new StringMatcher());
        matcherMap.put(Date.class, new DateMatcher());
    }

    public <T> Matcher<T> getMatcher(Class<T> clazz){
        return matcherMap.get(clazz);
    }
}
