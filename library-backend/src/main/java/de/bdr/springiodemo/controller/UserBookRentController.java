package de.bdr.springiodemo.controller;


import de.bdr.springiodemo.model.UserBookRent;
import de.bdr.springiodemo.model.dto.UserBookRentDTO;
import de.bdr.springiodemo.model.dto.UserDTO;
import de.bdr.springiodemo.service.UserBookRentServiceImpl;
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
@RequestMapping("/v1/rents")
public class UserBookRentController {

    @Autowired
    UserBookRentServiceImpl userBookRentService;

    @Autowired
    protected MappingUtil mappingUtil;

    @PostMapping("/createRent")
    public ResponseEntity<UserBookRentDTO> createRent(@Valid @RequestBody UserBookRentDTO userBookRentDTO) {

        UserBookRent result = userBookRentService.createUserBookRent(userBookRentDTO);

        return ResponseEntity.ok(mappingUtil.userBookToUserBookDTO(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBookRentDTO> getRentById(@PathVariable Long id) {

        UserBookRent result = userBookRentService.getUserBookRentById(id);

        return ResponseEntity.ok(mappingUtil.userBookToUserBookDTO(result));
    }

    @PostMapping("/getUserRent")
    public ResponseEntity<List<UserBookRentDTO>> getRentByUser(@Valid @RequestBody UserDTO userDTO) {

        List<UserBookRent> result = userBookRentService.getUserBookRentByUser(mappingUtil.userDTOToUser(userDTO));

        List<UserBookRentDTO> resultDTO = new ArrayList<>();
        result.forEach(rent -> resultDTO.add(mappingUtil.userBookToUserBookDTO(rent)));

        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping("/getBookRent")
    public ResponseEntity<List<UserBookRentDTO>> getRentByBook(@Valid @RequestBody UserBookRentDTO userBookRentDTO) {

        List<UserBookRent> result = userBookRentService.getUserBookRentByBook(userBookRentDTO.getBook());

        List<UserBookRentDTO> resultDTO = new ArrayList<>();
        result.forEach(rent -> resultDTO.add(mappingUtil.userBookToUserBookDTO(rent)));

        return ResponseEntity.ok(resultDTO);
    }

    @GetMapping("/allRent")
    public ResponseEntity<Iterable<UserBookRentDTO>> getAllRents() {

        Iterable<UserBookRent> result = userBookRentService.getUserBookRents();

        List<UserBookRentDTO> resultDTO = new ArrayList<>();
        result.forEach(rent -> resultDTO.add(mappingUtil.userBookToUserBookDTO(rent)));

        return ResponseEntity.ok(resultDTO);
    }

    @PostMapping("/updateRent")
    public ResponseEntity<UserBookRentDTO> updateRent(@Valid @RequestBody UserBookRentDTO userBookRentDTO) {

        UserBookRent result = userBookRentService.updateUserBookRent(userBookRentDTO);

        return ResponseEntity.ok(mappingUtil.userBookToUserBookDTO(result));
    }

    @PostMapping("/deleteRent")
    public ResponseEntity<String> deleteRent(@Valid @RequestBody UserBookRentDTO userBookRentDTO) throws IllegalArgumentException {

        userBookRentService.deleteUserBookRent(userBookRentDTO);

        return ResponseEntity.ok("Book successfully deleted");
    }

}
