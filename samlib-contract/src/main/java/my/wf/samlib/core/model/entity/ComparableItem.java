package my.wf.samlib.core.model.entity;

/**
 * Created by Serg on 27.04.2014.
 */
public interface ComparableItem<K> {
    String getFieldName();
    //Class<T> getProcessedClass();
    Customer getCustomer();
    Class<K> getFieldClassValue();
}
