package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testShouldSaveUserAndFindById() {
        //Given
        User user = new User("user", "user@gmail.com");

        //When
        userRepository.save(user);

        //Then
        long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        assertTrue(readUser.isPresent());

        //CleanUp
        userRepository.deleteById(id);
    }

    @Test
    public void testShouldDeleteUser() {
        //Given
        User user = new User("user", "user@gmail.com");
        userRepository.save(user);

        //When
        userRepository.deleteById(user.getId());

        //Then
        long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        assertFalse(readUser.isPresent());
    }
}

