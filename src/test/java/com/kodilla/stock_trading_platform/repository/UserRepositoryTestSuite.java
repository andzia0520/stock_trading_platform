package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.Wallet;
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

    @Autowired
    private WalletRepository walletRepository;

    @Test
    public void testShouldSaveUserAndFindById() {
        //Given
        User user = new User("hossa");

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
        User user = new User("bessa");
        userRepository.save(user);

        //When
        userRepository.deleteById(user.getId());

        //Then
        long id = user.getId();
        Optional<User> readUser = userRepository.findById(id);
        assertFalse(readUser.isPresent());
    }

   /* @Test
    public void testShouldDeleteUserWithWallet() {
        //Given
        User user = new User("user");
        userRepository.save(user);
        Wallet wallet = new Wallet(user);
        walletRepository.save(wallet);

        //When
        Optional<Wallet> checkWallet = walletRepository.findById(wallet.getId());
        Optional<User> checkUser = userRepository.findById(user.getId());
        userRepository.deleteById(user.getId());

        //Then
        assertFalse(checkUser.isPresent());
        assertFalse(checkWallet.isPresent());

       *//* //CleanUp
        for (User input : userRepository.findAll()) {
            userRepository.deleteById(input.getId());
        }*//*
    }*/
}
