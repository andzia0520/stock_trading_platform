package com.kodilla.stock_trading_platform.service;

import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDbService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(final long userId) {
        return userRepository.findById(userId);
    }

   /* public User getUserByLoginAndEmail(final String email) {
        return userRepository.findByEmail(email);
    }*/

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final String login, final String email) {
        userRepository.deleteByLoginOrEmail(login, email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
