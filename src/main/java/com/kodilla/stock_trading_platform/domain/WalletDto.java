package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalletDto {
    private long id;
    private Long userId;

    public WalletDto(Long userId) {
        this.userId = userId;
    }
}
