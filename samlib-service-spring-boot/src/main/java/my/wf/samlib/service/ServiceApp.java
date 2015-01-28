package my.wf.samlib.service;

import my.wf.samlib.core.factory.EntityFactory;
import my.wf.samlib.core.message.exception.StorageException;
import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.core.storage.AuthorStorage;
import my.wf.samlib.core.storage.CustomerStorage;
import my.wf.samlib.service.controller.Anonymous;
import my.wf.samlib.service.controller.AuthController;
import my.wf.samlib.service.controller.CustomerController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@ComponentScan
@EntityScan("my.wf.samlib.storage.jpa.model")
@EnableJpaRepositories
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableGlobalMethodSecurity
public class ServiceApp {
    public static void main(String[] args) throws StorageException {
        SpringApplication.run(ServiceApp.class, args);
        ApplicationContext ctx = SpringApplication.run(ServiceApp.class, args);

        CustomerStorage customerStorage = ctx.getBean(CustomerStorage.class);
        AuthorStorage authorStorage = ctx.getBean(AuthorStorage.class);
        EntityFactory entityFactory = ctx.getBean(EntityFactory.class);
        CustomerController customerController = ctx.getBean(CustomerController.class);

        Customer defaultCustomer = entityFactory.createInstance(Customer.class);
        defaultCustomer.setEnabled(true);
        defaultCustomer.setName("default");
        customerStorage.save(defaultCustomer);
        AuthController authController = ctx.getBean(AuthController.class);
        authController.login(defaultCustomer.getName(), "");
        System.out.println("\n\n\n\n\n\n\n\n\n" + authController.getActiveCustomer().getName());

        System.out.println("\n\n\n>>>>BEFORE " + customerController.getAuthors(null, false).size() + "<<<<\n\n");

        if(!authController.getActiveCustomer().getName().equals(Anonymous.NAME)){
            Author a = entityFactory.createInstance(Author.class);
            a.setLink("http://link_a");
            a.setName("AuthorA");
            a = authorStorage.save(a);
            customerController.addAuthor(a);
        }
        System.out.println("\n\n\n>>>>AFTER " + customerController.getAuthors(null, false).size() + "<<<<\n\n");

    }
}
