package my.wf.samlib.storage.hibernate.storage.factory;

import my.wf.samlib.core.factory.StorageFactory;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;

/**
 * Created by Serg on 14.01.2015.
 */
public class StorageHbFactory implements StorageFactory {
    @Override
    public AuthorStorage getAuthorStorage() {
        return null;
    }

    @Override
    public CustomerStorage getCustomerStorage() {
        return null;
    }
}
