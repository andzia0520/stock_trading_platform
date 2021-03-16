package com.kodilla.stock_trading_platform.mapper;

import com.kodilla.stock_trading_platform.domain.Wallet;
import com.kodilla.stock_trading_platform.domain.WalletDto;
import com.kodilla.stock_trading_platform.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WalletMapper {

    @Autowired
    private UserDbService userDbService;

    public Wallet mapToWallet(final WalletDto walletDto) {
        return new Wallet(
                walletDto.getId(),
                userDbService.getUserById(walletDto.getUserId()));
    }

    public WalletDto mapToWalletDto(final Wallet wallet) {
        return new WalletDto(
                wallet.getId(),
                wallet.getUser().getId());
    }
}
