package com.kodilla.stock_trading_platform.service;

import com.kodilla.stock_trading_platform.domain.Wallet;
import com.kodilla.stock_trading_platform.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletDbService {
    @Autowired
    private WalletRepository walletRepository;

    public Optional<Wallet> getWalletById(final long walletId) {
        return walletRepository.findById(walletId);
    }

    public Wallet saveWallet(final Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public void deleteWallet(final long walletId) {
        walletRepository.deleteById(walletId);
    }
}
