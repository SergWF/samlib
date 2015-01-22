package my.wf.samlib.storage.jpa;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Serg on 21.01.2015.
 */
@Configuration
@EnableJpaRepositories
@ComponentScan
public class JpaConfig {
}
