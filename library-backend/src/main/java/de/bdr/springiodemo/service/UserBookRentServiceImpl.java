package de.bdr.springiodemo.service;

import de.bdr.springiodemo.model.Book;
import de.bdr.springiodemo.model.User;
import de.bdr.springiodemo.model.UserBookRent;
import de.bdr.springiodemo.model.dto.UserBookRentDTO;
import de.bdr.springiodemo.repository.UserBookRentRepository;
import de.bdr.springiodemo.service.api.UserBookRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBookRentServiceImpl implements UserBookRentService {

    @Autowired
    private UserBookRentRepository userBookRentRepository;

    @Override
    public UserBookRent createUserBookRent(UserBookRentDTO userBookRentDTO) {

        UserBookRent userBookRent = new UserBookRent();
        userBookRent.setId(userBookRentDTO.getId());
        userBookRent.setBook(userBookRentDTO.getBook());
        userBookRent.setUser(userBookRentDTO.getUser());
        userBookRent.setLoanTime(userBookRentDTO.getLoanTime());
        userBookRent.setNote(userBookRentDTO.getNote());

        return userBookRentRepository.save(userBookRent);
    }

    @Override
    public List<UserBookRent> getUserBookRentByBook(Book book) {
        return userBookRentRepository.findByBook(book);
    }

    @Override
    public List<UserBookRent> getUserBookRentByUser(User user) {
        return userBookRentRepository.findByUser(user);
    }

    @Override
    public UserBookRent getUserBookRentById(Long id) {
        Optional<UserBookRent> userBookRent = userBookRentRepository.findById(id);

        return userBookRent.orElseGet(UserBookRent::new);
    }

    @Override
    public Iterable<UserBookRent> getUserBookRents() {
        return userBookRentRepository.findAll();
    }

    @Override
    public UserBookRent updateUserBookRent(UserBookRentDTO userBookRentDTO) {
        Long userBookRentId = userBookRentDTO.getId();

        Optional<UserBookRent> userBookRentById = userBookRentRepository.findById(userBookRentId);

        if (userBookRentById.isEmpty()) {
            return createUserBookRent(userBookRentDTO);
        } else {
            UserBookRent userBookRent = new UserBookRent();

            userBookRent.setId(userBookRentDTO.getId());
            userBookRent.setBook(userBookRentDTO.getBook());
            userBookRent.setUser(userBookRentDTO.getUser());
            userBookRent.setLoanTime(userBookRentDTO.getLoanTime());
            userBookRent.setNote(userBookRentDTO.getNote());

            return userBookRentRepository.save(userBookRent);
        }
    }

    @Override
    public void deleteUserBookRent(UserBookRentDTO userBookRentDTO) throws IllegalArgumentException {
        Long userBookRentId = userBookRentDTO.getId();

        try {
            Optional<UserBookRent> userBookRentById = userBookRentRepository.findById(userBookRentId);

            userBookRentById.ifPresent(userBookRent -> userBookRentRepository.delete(userBookRent));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Book with that title doesn't exist!");
        }
    }
}
