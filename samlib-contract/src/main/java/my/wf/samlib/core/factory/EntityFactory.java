package my.wf.samlib.core.factory;

import my.wf.samlib.core.model.entity.BaseEntity;

/**
 * Created by Serg on 21.01.2015.
 */
public interface EntityFactory {
    <T extends BaseEntity> T createInstance(Class<T> clazz);
}
