package de.bdr.springiodemo.service.api;

import de.bdr.springiodemo.exception.AuthorAlreadyExistsException;
import de.bdr.springiodemo.exception.AuthorNotFoundException;
import de.bdr.springiodemo.model.Author;
import de.bdr.springiodemo.model.dto.AuthorDTO;

import java.util.List;

/**
 * Author service interface which communicates with Author repository
 */
public interface AuthorService {

    /**
     * Method creates an author with specific data
     *
     * @param authorDTO - data for creating a new author
     * @return Newly created Author
     * @throws AuthorAlreadyExistsException - if author already exist
     */
    public Author createAuthor(AuthorDTO authorDTO) throws AuthorAlreadyExistsException;

    /**
     * Method retrieves an author by its last name
     *
     * @param lastName - author's last name
     * @return List of Author with specific last name
     * @throws AuthorNotFoundException - if there are no authors with that last name
     */
    public List<Author> getAuthorByLastName(String lastName) throws AuthorNotFoundException;

    /**
     * Method retrieves an author by its id
     *
     * @param id - author's id
     * @return Author with specific id
     * @throws AuthorNotFoundException - if there are no authors with that id
     */
    public Author getAuthorById(Long id) throws AuthorNotFoundException;

    /**
     * Method retrieves all authors
     *
     * @return Iterable of Author
     * @throws AuthorNotFoundException - if there are no authors in database
     */
    public Iterable<Author> getAuthors() throws AuthorNotFoundException;

    /**
     * Method updates an existing author or creates a new one
     *
     * @param authorDTO - author's data
     * @return Author - updated or created
     */
    public Author updateAuthor(AuthorDTO authorDTO);

    /**
     * Method deletes an author with specific data
     *
     * @param authorDTO - author's data
     * @throws AuthorNotFoundException - if there are no authors with that data
     */
    public void deleteAuthor(AuthorDTO authorDTO) throws AuthorNotFoundException;

}
