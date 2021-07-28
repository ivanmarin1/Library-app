package de.bdr.springiodemo.service;

import de.bdr.springiodemo.exception.BookAlreadyExistsException;
import de.bdr.springiodemo.exception.BookNotFoundException;
import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.dto.BookDTO;
import de.bdr.springiodemo.repository.BookRepository;
import de.bdr.springiodemo.service.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(BookDTO bookDTO) throws BookAlreadyExistsException {

        List<Book> bookByTitle = bookRepository.findByTitle(bookDTO.getTitle());

        if (!bookByTitle.isEmpty())
            throw new BookAlreadyExistsException(String.format("Book with title %s already exist!", bookDTO.getTitle()));

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthorID(bookDTO.getAuthorID());
        book.setYear(bookDTO.getYear());
        book.setNote(bookDTO.getNote());

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBookByTitle(String title) throws BookNotFoundException {

        List<Book> bookByTitle = bookRepository.findByTitle(title);

        if (bookByTitle.isEmpty()) {
            throw new BookNotFoundException(String.format("Book with title %s doesn't exist!", title));
        } else {
            return bookByTitle;
        }
    }

    @Override
    public Book getBookById(Long id) throws BookNotFoundException {

        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(String.format("Book with Id %d doesn't exist!", id)));
    }

    @Override
    public Iterable<Book> getBooks() throws BookNotFoundException {

        Iterable<Book> books = bookRepository.findAll();

        if (books.iterator().hasNext()) {
            return books;
        } else {
            throw new BookNotFoundException("There are no books in database!");
        }
    }

    @Override
    public Book updateBook(BookDTO bookDTO) {
        Long bookId = bookDTO.getId();

        Optional<Book> bookById = bookRepository.findById(bookId);

        if (bookById.isEmpty()) {
            Book book = new Book();
            book.setId(bookDTO.getId());
            book.setTitle(bookDTO.getTitle());
            book.setAuthorID(bookDTO.getAuthorID());
            book.setYear(bookDTO.getYear());
            book.setNote(bookDTO.getNote());

            return book;
        } else {
            Book book = bookById.get();

            book.setTitle(bookDTO.getTitle());
            book.setAuthorID(bookDTO.getAuthorID());
            book.setYear(bookDTO.getYear());
            book.setNote(bookDTO.getNote());

            return bookRepository.save(book);
        }
    }

    @Override
    public void deleteBook(BookDTO bookDTO) throws BookNotFoundException {

        Long bookId = bookDTO.getId();

        Optional<Book> bookById = bookRepository.findById(bookId);

        if (bookById.isPresent()) {
            bookRepository.delete(bookById.get());
        } else {
            throw new BookNotFoundException(String.format("Book with Id %d doesn't exist!", bookId));
        }
    }
}
