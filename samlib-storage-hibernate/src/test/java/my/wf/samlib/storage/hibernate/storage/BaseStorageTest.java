package my.wf.samlib.storage.hibernate.storage;

import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseStorageTest  {
    protected SessionFactory sessionFactory;
    protected List<Customer> customers ;
    protected List<Author> authors;

    @SuppressWarnings("Deprecated")
    public void setUp() throws StorageException {
        Configuration configure = new Configuration().configure();
        configure.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        this.sessionFactory = configure.buildSessionFactory();
        AuthorStorageHb storage = new AuthorStorageHb(sessionFactory);
        System.out.println("before authors found: " + storage.list(null, null).size());
        initData();
        System.out.println("after authors found: " + storage.list(null, null).size());
    }

    protected void initData() throws StorageException {
        initAuthors();
        initCustomers();
    }

    private void initAuthors() throws StorageException {
        AuthorStorageHb authorStorage = new AuthorStorageHb(sessionFactory);
        authors = new ArrayList<>();
        for(long i = 0; i<6; i++){
            Author author = EntityHbCreator.createAuthorFull(i, 5);
            Author saved = authorStorage.save(author);
            authors.add(saved);
        }
    }

    private void initCustomers() throws StorageException {
        CustomerStorageHb customerStorage = new CustomerStorageHb(sessionFactory);
        customers = new ArrayList<>();
        for(long i = 0; i<3; i++){
            customers.add(customerStorage.save(EntityHbCreator.createCustomer(i)));
        }
    }
}
