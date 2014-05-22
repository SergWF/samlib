package my.wf.samlib.storage.json.filtering;

import my.wf.samlib.core.dataextract.filtering.FilterItem;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public enum DataMatcher {
    STRING {
        @Override
        protected <K> boolean matchByClass(K value, K pattern) {
            return false;
        }
    }, DATE {
        @Override
        protected <K> boolean matchByClass(K value, K pattern) {
            return false;
        }
    }, DEFAULT {
        @Override
        protected <K> boolean matchByClass(K value, K pattern) {
            return false;
        }
    };

    protected abstract <K> boolean matchByClass(K value, K pattern);

    private static DataMatcher getByClass(Class clazz){
        for(DataMatcher matcher: DataMatcher.values()){
            if(matcher.name().equals(clazz.getSimpleName().toUpperCase())){
                return matcher;
            }
        }
        return DEFAULT;
    }

    public static <K> boolean match(K value, FilterItem<K> item){
        return getByClass(item.getFieldValueClass().getClass()).matchByClass(value, item.getFilterValue());
    }
}
