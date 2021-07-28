package de.bdr.springiodemo.service.api;

import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.User;
import de.bdr.springiodemo.model.UserBookRent;
import de.bdr.springiodemo.model.dto.UserBookRentDTO;

import java.util.List;

/**
 * UserBookRent service interface which communicates with UserBookRent repository
 */
public interface UserBookRentService {

    /**
     * Method creates an rent with specific data
     * @param userBookRentDTO - rent's data
     * @return UserBookRent
     */
    public UserBookRent createUserBookRent(UserBookRentDTO userBookRentDTO);

    /**
     * Method retrieves the rents of specific book
     * @param book
     * @return - List of UserBookRent with specific book
     */
    public List<UserBookRent> getUserBookRentByBook(Book book);

    /**
     * Method retrieves the rents of specific user
     * @param user
     * @return - List of UserBookRent with specific user
     */
    public List<UserBookRent> getUserBookRentByUser(User user);

    /**
     * Method retrieves the rent with specific id
     * @param id
     * @return UserBookRent with specific id
     */
    public UserBookRent getUserBookRentById(Long id);

    /**
     * Method retrieves all rents
     * @return Iterable of all UserBookRent
     */
    public Iterable<UserBookRent> getUserBookRents();

    /**
     * Method updates existing rent or creates a new one
     * @param userBookRentDTO
     * @return UserBookRent - updated or created
     */
    public UserBookRent updateUserBookRent(UserBookRentDTO userBookRentDTO);

    /**
     * Method deletes the rent with specific data
     * @param userBookRentDTO
     * @throws IllegalArgumentException - if there are no rents with that data
     */
    public void deleteUserBookRent(UserBookRentDTO userBookRentDTO) throws IllegalArgumentException;
}
