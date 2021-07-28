package de.bdr.springiodemo.service;

import de.bdr.springiodemo.exception.AuthorAlreadyExistsException;
import de.bdr.springiodemo.exception.AuthorNotFoundException;
import de.bdr.springiodemo.model.Author;
import de.bdr.springiodemo.model.dto.AuthorDTO;
import de.bdr.springiodemo.repository.AuthorRepository;
import de.bdr.springiodemo.service.api.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author createAuthor(AuthorDTO authorDTO) throws AuthorAlreadyExistsException {

        List<Author> authorByName = authorRepository.findByLastName(authorDTO.getLastName());

        if (!authorByName.isEmpty())
            throw new AuthorAlreadyExistsException(String.format("Author with last name %s already exist!", authorDTO.getLastName()));

        Author author = new Author();
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setGenre(authorDTO.getGenre());

        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthorByLastName(String lastName) throws AuthorNotFoundException {

        List<Author> authorByLastName = authorRepository.findByLastName(lastName);

        if (authorByLastName.isEmpty()) {
            throw new AuthorNotFoundException(String.format("Author with last name %s doesn't exist!", lastName));
        } else {
            return authorByLastName;
        }

    }

    @Override
    public Author getAuthorById(Long id) throws AuthorNotFoundException {

        return authorRepository.findById(id).orElseThrow(() ->new AuthorNotFoundException(String.format("Author with Id %d doesn't exist!", id)));
    }

    @Override
    public Iterable<Author> getAuthors() throws AuthorNotFoundException {

        Iterable<Author> authors = authorRepository.findAll();

        if (authors.iterator().hasNext()) {
            return authors;
        } else {
            throw new AuthorNotFoundException("There are no authors in database!");
        }
    }

    @Override
    public Author updateAuthor(AuthorDTO authorDTO) {

        String authorLastName = authorDTO.getLastName();

        List<Author> authorByLastName = authorRepository.findByLastName(authorLastName);

        if (authorByLastName.isEmpty()) {
            Author author = new Author();

            author.setFirstName(authorDTO.getFirstName());
            author.setLastName(authorDTO.getLastName());
            author.setGenre(authorDTO.getGenre());

            return authorRepository.save(author);
        } else {
            Author author = authorByLastName.get(0);

            author.setFirstName(authorDTO.getFirstName());
            author.setLastName(authorDTO.getLastName());
            author.setGenre(authorDTO.getGenre());

            return authorRepository.save(author);
        }
    }

    @Override
    public void deleteAuthor(AuthorDTO authorDTO) throws AuthorNotFoundException {
        String authorLastName = authorDTO.getLastName();

        List<Author> authorByLastName = authorRepository.findByLastName(authorLastName);

        if (!authorByLastName.isEmpty()) {
            Author author = authorByLastName.get(0);

            authorRepository.delete(author);
        } else {
            throw new AuthorNotFoundException(String.format("Author with last name %s doesn't exist!", authorLastName));
        }
    }
}
