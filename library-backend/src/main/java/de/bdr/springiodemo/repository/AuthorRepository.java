package de.bdr.springiodemo.repository;

import de.bdr.springiodemo.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author repository interface
 * Has method for finding authors with specific last name
 */
@Component
public interface AuthorRepository extends CrudRepository<Author, Long> {

    /**
     * Method finds a author by its last name
     * @param lastName - user's last name
     * @return List of User with specific last name
     */
    List<Author> findByLastName(@Param("lastName") String lastName);

}
