package de.bdr.springiodemo.repository;

import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.User;
import de.bdr.springiodemo.model.UserBookRent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserBookRent repository interface
 * Has methods for finding rents with specific user or book
 */
@Component
public interface UserBookRentRepository extends CrudRepository<UserBookRent, Long> {

    /**
     * Method finds all rents of specific book
     * @param book
     * @return List of rents of specific book
     */
    List<UserBookRent> findByBook(@Param("book") Book book);

    /**
     * Method finds all rents of specific user
     * @param user
     * @return List of rents of specific user
     */
    List<UserBookRent> findByUser(@Param("User") User user);
}
