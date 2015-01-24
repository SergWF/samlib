package my.wf.samlib.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan
@EntityScan("my.wf.samlib.storage.jpa.model")
@EnableJpaRepositories
@EnableAutoConfiguration
public class ServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ServiceApp.class, args);
        ApplicationContext ctx = SpringApplication.run(ServiceApp.class, args);
    }
}
