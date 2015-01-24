package my.wf.samlib.storage.jpa.factory;

import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.storage.jpa.model.AuthorJpa;
import my.wf.samlib.storage.jpa.model.CustomerJpa;
import my.wf.samlib.storage.jpa.model.WritingJpa;
import org.springframework.stereotype.Component;

/**
 * Created by Serg on 24.01.2015.
 */
@Component
public class EntityFactoryJpaImpl implements EntityFactory {

    private enum FactoryJpa{
        AUTHOR {
            @Override
            public AuthorJpa newEntity() {
                return new AuthorJpa();
            }
        }, WRITING {
            @Override
            public WritingJpa newEntity() {
                return new WritingJpa();
            }
        },CUSTOMER {
            @Override
            public CustomerJpa newEntity() {
                return new CustomerJpa();
            }
        };
        public abstract BaseEntity newEntity();
    }


    @Override
    public <T extends BaseEntity> T createInstance(Class<T> clazz) {
        return (T) FactoryJpa.valueOf(clazz.getSimpleName().toUpperCase()).newEntity();
    }
}
