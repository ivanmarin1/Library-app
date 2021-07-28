package de.bdr.springiodemo.controller;

import de.bdr.springiodemo.exception.UserAlreadyExistsException;
import de.bdr.springiodemo.exception.UserNotFoundException;
import de.bdr.springiodemo.model.User;
import de.bdr.springiodemo.model.dto.UserDTO;
import de.bdr.springiodemo.service.UserServiceImpl;
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
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    protected MappingUtil mappingUtil;

    /**
     * Post request for creating a new user with userDTO param in request body
     *
     * @param userDTO - user's fields
     * @return ResponseEntity.ok with userDTO
     */
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws UserAlreadyExistsException {

        User result = userService.createUser(userDTO);

        return ResponseEntity.ok(mappingUtil.userToUserDTO(result));
    }

    /**
     * Get request for retrieving an user by its id
     *
     * @param id - user's id
     * @return ResponseEntity.ok with userDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws UserNotFoundException {

        User result = userService.getUserById(id);

        return ResponseEntity.ok(mappingUtil.userToUserDTO(result));
    }

    /**
     * Get request for retrieving all users
     *
     * @return ResponseEntity.ok with userDTOs in List
     */
    @GetMapping("/")
    public ResponseEntity<Iterable<UserDTO>> getUsers() throws UserNotFoundException {

        Iterable<User> result = userService.getUsers();

        List<UserDTO> resultDTO = new ArrayList<>();
        result.forEach(user -> resultDTO.add(mappingUtil.userToUserDTO(user)));

        return ResponseEntity.ok(resultDTO);
    }

    /**
     * Post request for updating a current user or creating a new one if desired user doesn't exist
     *
     * @param userDTO - user's fields
     * @return ResponseEntity.ok with userDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {

        User result = userService.updateUser(userDTO);

        return ResponseEntity.ok(mappingUtil.userToUserDTO(result));
    }

    /**
     * Post request for deleting the user
     *
     * @param userDTO - user's fields
     * @return ResponseEntity.ok with "User successfully deleted" message
     * @throws IllegalArgumentException if user doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@Valid @RequestBody UserDTO userDTO) throws UserNotFoundException {

        userService.deleteUser(userDTO);

        return ResponseEntity.ok("User successfully deleted");
    }
}
