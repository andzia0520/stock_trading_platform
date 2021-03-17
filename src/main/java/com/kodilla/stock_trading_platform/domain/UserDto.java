package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private long id;
    private String login;
    private String email;

    public UserDto(String login, String email) {
        this.login = login;
        this.email = email;
    }

}
