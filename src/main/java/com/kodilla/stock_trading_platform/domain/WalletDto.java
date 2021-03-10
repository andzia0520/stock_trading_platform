package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalletDto {
    private long id;
    private UserDto userDto;

    public WalletDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
