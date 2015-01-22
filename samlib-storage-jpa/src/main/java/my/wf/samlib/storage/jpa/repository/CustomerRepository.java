package my.wf.samlib.storage.jpa.repository;

import my.wf.samlib.core.model.entity.Customer;
import my.wf.samlib.storage.jpa.model.CustomerJpa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Serg on 22.01.2015.
 */
public interface CustomerRepository extends JpaRepository<CustomerJpa, Long> {
    CustomerJpa getByName(String customerName);
}
