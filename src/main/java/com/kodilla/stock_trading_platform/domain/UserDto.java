package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private long id;
    private String login;
    private String email;
}
