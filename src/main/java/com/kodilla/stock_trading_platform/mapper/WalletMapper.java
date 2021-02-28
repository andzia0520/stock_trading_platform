package com.kodilla.stock_trading_platform.mapper;

import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.UserDto;
import com.kodilla.stock_trading_platform.domain.Wallet;
import com.kodilla.stock_trading_platform.domain.WalletDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletMapper {
    @Autowired
    private UserMapper userMapper;

    public Wallet mapToWallet(final WalletDto walletDto) {
        return new Wallet(
                walletDto.getId(),
                userMapper.mapToUser(walletDto.getUserDto()));
    }

    public WalletDto mapToWalletDto(final Wallet wallet) {
        return new WalletDto(
                wallet.getId(),
                userMapper.mapToUserDto(wallet.getUser()));
    }

    public List<WalletDto> mapToWalletDtoList(final List<Wallet> walletsList) {
        return walletsList.stream()
                .map(w -> new WalletDto(w.getId(), userMapper.mapToUserDto(w.getUser())))
                .collect(Collectors.toList());
    }
}
