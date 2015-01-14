package my.wf.samlib.core.factory;

import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;

/**
 * Created by Serg on 14.01.2015.
 */
public interface StorageFactory {
    AuthorStorage getAuthorStorage();
    CustomerStorage getCustomerStorage();
}
