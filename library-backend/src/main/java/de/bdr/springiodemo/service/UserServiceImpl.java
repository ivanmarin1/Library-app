package de.bdr.springiodemo.service;

import de.bdr.springiodemo.exception.UserAlreadyExistsException;
import de.bdr.springiodemo.exception.UserNotFoundException;
import de.bdr.springiodemo.model.User;
import de.bdr.springiodemo.model.dto.UserDTO;
import de.bdr.springiodemo.repository.UserRepository;
import de.bdr.springiodemo.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(UserDTO userDTO) throws UserAlreadyExistsException {

        List<User> userByLastName = userRepository.findByLastName(userDTO.getLastName());

        if (!userByLastName.isEmpty())
            throw new UserAlreadyExistsException(String.format("User with last name %s already exist!", userDTO.getLastName()));

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setAddress(userDTO.getAddress());

        return userRepository.save(user);
    }

    public List<User> getUserByLastName(String lastName) throws UserNotFoundException {

        List<User> userByLastName = userRepository.findByLastName(lastName);

        if (userByLastName.isEmpty()) {
            throw new UserNotFoundException(String.format("User with last name %s doesn't exist!", lastName));
        } else {
            return userByLastName;
        }
    }

    public User getUserById(Long id) throws UserNotFoundException {

        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with Id %d doesn't exist!", id)));
    }

    public Iterable<User> getUsers() throws UserNotFoundException {

        Iterable<User> users = userRepository.findAll();

        if (users.iterator().hasNext()) {
            return users;
        } else {
            throw new UserNotFoundException("There are no users in database!");
        }
    }

    public User updateUser(UserDTO userDTO) {

        String userLastName = userDTO.getLastName();

        List<User> userByLastName = userRepository.findByLastName(userLastName);

        if (userByLastName.isEmpty()) {
            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setAddress(userDTO.getAddress());

            return userRepository.save(user);
        } else {
            User user = userByLastName.get(0);

            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setAddress(userDTO.getAddress());

            return userRepository.save(user);
        }
    }

    public void deleteUser(UserDTO userDTO) throws UserNotFoundException {

        String userLastName = userDTO.getLastName();

        List<User> userByLastName = userRepository.findByLastName(userLastName);

        if (!userByLastName.isEmpty()) {
            User user = userByLastName.get(0);

            userRepository.delete(user);
        } else
            throw new UserNotFoundException(String.format("User with last name %s doesn't exist!", userLastName));
    }
}
