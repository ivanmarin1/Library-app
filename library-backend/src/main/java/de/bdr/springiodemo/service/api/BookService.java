package de.bdr.springiodemo.service.api;

import de.bdr.springiodemo.exception.BookAlreadyExistsException;
import de.bdr.springiodemo.exception.BookNotFoundException;
import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.dto.BookDTO;
import java.util.List;

/**
 * Book service interface which communicates with Book repository
 */
public interface BookService  {

    /**
     * Method creates a book with specific data
     * @param bookDTO - data for creating a new book
     * @return Newly created Book
     * @throws BookAlreadyExistsException - if book already exist
     */
    public Book createBook(BookDTO bookDTO) throws BookAlreadyExistsException;

    /**
     * Method retrieves a book by its title
     * @param title
     * @return List of Book with specific title
     * @throws BookNotFoundException - if book doesn't exist
     */
    public List<Book> getBookByTitle(String title) throws BookNotFoundException;

    /**
     * Method retrieves a book by its id
     * @param id
     * @return Book with specific id
     * @throws BookNotFoundException - if book doesn't exist
     */
    public Book getBookById(Long id) throws BookNotFoundException;

    /**
     * Method retrieves all books
     * @return Iterable of Book
     * @throws BookNotFoundException - if there are no books in database
     */
    public Iterable<Book> getBooks() throws BookNotFoundException;

    /**
     * Method updates an existing book or creates a new one
     * @param bookDTO
     * @return Book - updated or created
     */
    public Book updateBook(BookDTO bookDTO);

    /**
     * Method deletes a book with specific data
     * @param bookDTO
     * @throws BookNotFoundException - if book doesn't exist
     */
    public void deleteBook(BookDTO bookDTO) throws BookNotFoundException;
}
