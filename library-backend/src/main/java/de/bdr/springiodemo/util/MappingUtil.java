package de.bdr.springiodemo.util;

import de.bdr.springiodemo.model.Author;
import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.User;
import de.bdr.springiodemo.model.UserBookRent;
import de.bdr.springiodemo.model.dto.AuthorDTO;
import de.bdr.springiodemo.model.dto.BookDTO;
import de.bdr.springiodemo.model.dto.UserBookRentDTO;
import de.bdr.springiodemo.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface MappingUtil {

    public MappingUtil INSTANCE = Mappers.getMapper(MappingUtil.class );

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);

    BookDTO bookToBookDTO(Book book);
    Book bookDTOToBook(BookDTO bookDTO);

    AuthorDTO authorToAuthorDTO(Author author);
    Author authorDTOToAuthor(AuthorDTO authorDTO);

    UserBookRentDTO userBookToUserBookDTO(UserBookRent userBookRent);
    UserBookRent userBookDTOToUserBook(UserBookRentDTO userBookRentDTO);

}
