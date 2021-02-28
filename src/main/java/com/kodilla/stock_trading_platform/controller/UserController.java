package com.kodilla.stock_trading_platform.controller;

import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.mapper.UserMapper;
import com.kodilla.stock_trading_platform.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserDbService userDbService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createUser")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto((userDbService.saveUser(userMapper.mapToUser(userDto))));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userDbService.getUserById(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userDbService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public void updateUser(@RequestBody UserDto userDto) {
        userDbService.saveUser(userMapper.mapToUser(userDto));
    }
}
