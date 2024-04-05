package com.galactics.airlines.reservations.service;

import com.galactics.airlines.reservations.model.User;
import com.galactics.airlines.reservations.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void createUser() {
        User user = new User();
        user.setAddress("123 Main St");
        user.setBirthday(new Date());
        user.setEmail("john.doe@gmail.com");
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setPhoneNumber("123-456-7890");

        userRepository.save(user);
    }
}
