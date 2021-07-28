package de.bdr.springiodemo.controller;

import de.bdr.springiodemo.exception.AuthorAlreadyExistsException;
import de.bdr.springiodemo.exception.AuthorNotFoundException;
import de.bdr.springiodemo.model.Author;
import de.bdr.springiodemo.model.dto.AuthorDTO;
import de.bdr.springiodemo.service.AuthorServiceImpl;
import de.bdr.springiodemo.util.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/v1/authors")
public class AuthorController {

    @Autowired
    AuthorServiceImpl authorService;

    @Autowired
    protected MappingUtil mappingUtil;

    @PostMapping("/createAuthor")
    public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) throws AuthorAlreadyExistsException {

        Author result = authorService.createAuthor(authorDTO);

        return ResponseEntity.ok(mappingUtil.authorToAuthorDTO(result));
    }

    @PostMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) throws AuthorNotFoundException {

        Author result = authorService.getAuthorById(id);

        return ResponseEntity.ok(mappingUtil.authorToAuthorDTO(result));
    }

    @GetMapping("/allAuthors")
    public ResponseEntity<Iterable<AuthorDTO>> getAuthors() throws AuthorNotFoundException {

        Iterable<Author> result = authorService.getAuthors();

        List<AuthorDTO> resultDTO = new ArrayList<>();
        result.forEach(author -> resultDTO.add(mappingUtil.authorToAuthorDTO(author)));

        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping("/updateAuthor")
    public ResponseEntity<AuthorDTO> updateBook(@Valid @RequestBody AuthorDTO authorDTO) {

        Author result = authorService.updateAuthor(authorDTO);

        return ResponseEntity.ok(mappingUtil.authorToAuthorDTO(result));
    }

    @PostMapping("/deleteAuthor")
    public ResponseEntity<String> deleteBook(@Valid @RequestBody AuthorDTO authorDTO) throws AuthorNotFoundException {

        authorService.deleteAuthor(authorDTO);

        return ResponseEntity.ok("Author successfully deleted");
    }
}
