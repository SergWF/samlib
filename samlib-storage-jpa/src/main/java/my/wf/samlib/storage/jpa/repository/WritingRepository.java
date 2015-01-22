package my.wf.samlib.storage.jpa.repository;

import my.wf.samlib.storage.jpa.model.WritingJpa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Serg on 22.01.2015.
 */
public interface WritingRepository extends JpaRepository<WritingJpa, Long> {
}
