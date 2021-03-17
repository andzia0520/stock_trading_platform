package com.kodilla.stock_trading_platform.mapper;

import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getEmail());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getEmail());
    }
}

