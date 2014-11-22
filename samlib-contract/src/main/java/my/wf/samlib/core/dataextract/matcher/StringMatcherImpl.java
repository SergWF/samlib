package my.wf.samlib.core.dataextract.matcher;

public class StringMatcherImpl extends MatcherImpl<String> {

    @Override
    public boolean match(String value, String pattern) {
        if(null == pattern){
            return true;
        }
        return (null != value) && value.trim().toLowerCase().contains(pattern.trim().toLowerCase());
    }
}
