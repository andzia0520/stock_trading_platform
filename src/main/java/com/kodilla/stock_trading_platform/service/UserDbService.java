package com.kodilla.stock_trading_platform.service;

import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDbService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(final long userId) throws NotFoundException {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User with id " + userId + " doesn't exist in our system.")
        );
    }

    public User getUserByEmail(final String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User with email " + email + " doesn't exist in our system.")
        );
    }

    public User saveUser(final User user) throws UserAlreadyExistException {
        if (!userRepository.findByEmail(user.getEmail()).isPresent()) {
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistException("User with this email already exists, " +
                    "please check your id to see your wallet or make transaction");
        }
    }

    public void updateUser(final User user) throws NotFoundException {
        if (userRepository.findById(user.getId()).isPresent()) {
            userRepository.save(user);
        } else {
            throw new NotFoundException("User with id " + user.getId() +
                    " doesn't exist in our system.");
        }
    }

    public void deleteById(final long userId) {
        userRepository.deleteById(userId);
    }

}

