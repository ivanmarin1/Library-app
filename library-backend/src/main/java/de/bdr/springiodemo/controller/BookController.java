package de.bdr.springiodemo.controller;

import de.bdr.springiodemo.exception.BookAlreadyExistsException;
import de.bdr.springiodemo.exception.BookNotFoundException;
import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.dto.BookDTO;
import de.bdr.springiodemo.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import de.bdr.springiodemo.util.MappingUtil;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/v1/books")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @Autowired
    protected MappingUtil mappingUtil;

    @PostMapping("/createBook")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) throws BookAlreadyExistsException {

        Book result = bookService.createBook(bookDTO);

        return ResponseEntity.ok(mappingUtil.bookToBookDTO(result));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) throws BookNotFoundException {

        Book result = bookService.getBookById(id);

        return ResponseEntity.ok(mappingUtil.bookToBookDTO(result));
    }

    @GetMapping("/allBooks")
    public ResponseEntity<Iterable<BookDTO>> getBooks()  throws BookNotFoundException{

        Iterable<Book> result = bookService.getBooks();

        List<BookDTO> resultDTO = new ArrayList<>();
        result.forEach(book -> resultDTO.add(mappingUtil.bookToBookDTO(book)));

        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping("/updateBook")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO bookDTO) {

        Book result = bookService.updateBook(bookDTO);

        return ResponseEntity.ok(mappingUtil.bookToBookDTO(result));
    }

    @PostMapping("/deleteBook")
    public ResponseEntity<String> deleteBook(@Valid @RequestBody BookDTO bookDTO) throws BookNotFoundException {

        bookService.deleteBook(bookDTO);

        return ResponseEntity.ok("Book successfully deleted");
    }
}
