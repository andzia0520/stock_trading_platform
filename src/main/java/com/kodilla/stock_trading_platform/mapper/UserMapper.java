package com.kodilla.stock_trading_platform.mapper;

import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getLogin());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getLogin());
    }

    public List<UserDto> mapToUserDtoList(final List<User> usersList) {
        return usersList.stream()
                .map(u -> new UserDto(u.getId(), u.getLogin()))
                .collect(Collectors.toList());
    }
}
