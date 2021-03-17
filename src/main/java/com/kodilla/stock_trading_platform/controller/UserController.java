package com.kodilla.stock_trading_platform.controller;

import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.mapper.UserMapper;
import com.kodilla.stock_trading_platform.service.UserAlreadyExistException;
import com.kodilla.stock_trading_platform.service.UserDbService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserDbService userDbService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public UserDto createUser(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        return userMapper.mapToUserDto((userDbService.saveUser(userMapper.mapToUser(userDto))));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public void updateUser(@RequestBody UserDto userDto) throws NotFoundException {
        userDbService.updateUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userDbService.deleteById(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUser(@PathVariable Long userId) throws NotFoundException {
        return userMapper.mapToUserDto(userDbService.getUserById(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/usersByMail/{email}")
    public UserDto getUserByEmail(@PathVariable String email) throws NotFoundException {
        return userMapper.mapToUserDto(userDbService.getUserByEmail(email));
    }
}

