package my.wf.samlib.storage.jpa.repository;

import my.wf.samlib.core.model.entity.Author;
import my.wf.samlib.storage.jpa.model.AuthorJpa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Serg on 22.01.2015.
 */
public interface AuthorRepository extends JpaRepository<AuthorJpa, Long> {
    AuthorJpa findByLink(String authorLink);
}
