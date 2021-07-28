package de.bdr.springiodemo.service.api;

import de.bdr.springiodemo.exception.UserAlreadyExistsException;
import de.bdr.springiodemo.exception.UserNotFoundException;
import de.bdr.springiodemo.model.User;
import de.bdr.springiodemo.model.dto.UserDTO;

import java.util.List;

/**
 * User service which communicates with User repository
 */
public interface UserService {

    /**
     * Method for creating and saving a new user to the database
     * @param userDTO - user's fields
     * @return User which is created
     */
    public User createUser(UserDTO userDTO) throws UserAlreadyExistsException;

    /**
     * Method for retrieving an user by last name
     * @param lastName - user's last name
     * @return List of User with that last name
     */
    public List<User> getUserByLastName(String lastName) throws UserNotFoundException;

    /**
     * Method for retrieving an user by id
     * @param id - user's id
     * @return User with that id
     */
    public User getUserById(Long id) throws UserNotFoundException;

    /**
     * Method for retrieving all users
     * @return Iterable of User
     */
    public Iterable<User> getUsers() throws UserNotFoundException;

    /**
     * Method for updating current user or creating a new one if that one doesn't exist
     * @param userDTO - user's fields
     * @return User - updated user
     */
    public User updateUser(UserDTO userDTO);

    /**
     * Method for deleting an existing user
     * @param userDTO - user's fields
     * @throws IllegalArgumentException if user doesn't exist
     */
    public void deleteUser(UserDTO userDTO) throws IllegalArgumentException, UserNotFoundException;
}
