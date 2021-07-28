package de.bdr.springiodemo.repository;

import de.bdr.springiodemo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User repository interface
 * Has method for finding users with specific last name
 */
@Component
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Method finds a user by its last name
     * @param lastName - user's last name
     * @return List of User with specific last name
     */
    List<User> findByLastName(@Param("name") String lastName);

}
