package my.wf.samlib.core.dataextract.filtering;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class FilterItem<K> {
    private String fieldName;
    private String filterValue;

    public FilterItem(String fieldName, String filterValue) {
        this.fieldName = fieldName;
        this.filterValue = filterValue;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public String getFieldName() {
        return fieldName;
    }
}
