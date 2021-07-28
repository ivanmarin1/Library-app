package de.bdr.springiodemo.repository;

import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Book repository interface
 * Has method for finding books with specific title
 */
@Component
public interface BookRepository extends CrudRepository<Book, Long> {

    /**
     * Method finds a book or books by its title
     * @param title - book's title
     * @return List of books with specific title
     */
    List<Book> findByTitle(@Param("title") String title);
}
