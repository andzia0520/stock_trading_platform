package com.kodilla.stock_trading_platform.controller;

import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.mapper.UserMapper;
import com.kodilla.stock_trading_platform.service.UserAlreadyExistException;
import com.kodilla.stock_trading_platform.service.UserDbService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserDbService userDbService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public UserDto createUser(@RequestBody UserDto userDto) throws UserAlreadyExistException {
        return userMapper.mapToUserDto((userDbService.saveUser(userMapper.mapToUser(userDto))));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws NotFoundException {
        return userMapper.mapToUserDto(userDbService.getUserById(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUserByEmail")
    public UserDto getUserByEmail(@RequestParam String email) throws NotFoundException {
        return userMapper.mapToUserDto(userDbService.getUserByEmail(email));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userDbService.deleteById(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public void updateUser(@RequestBody UserDto userDto) throws NotFoundException {
        userDbService.updateUser(userMapper.mapToUser(userDto));
    }
}

