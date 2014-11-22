package my.wf.samlib.core.dataextract.matcher;

public class StringMatcher implements Matcher<String> {

    @Override
    public boolean match(String value, String pattern) {
        if(null == pattern || 0 == pattern.trim().length()){
            return true;
        }
        return (null != value) && value.trim().toLowerCase().contains(pattern.trim().toLowerCase());
    }
}
