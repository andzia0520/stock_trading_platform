package com.kodilla.stock_trading_platform.service;

import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.Wallet;
import com.kodilla.stock_trading_platform.repository.TransactionRepository;
import com.kodilla.stock_trading_platform.repository.WalletRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletDbService {
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Wallet getWalletById(final long walletId) throws NotFoundException {
        return walletRepository.findById(walletId).orElseThrow(
                () -> new NotFoundException("Wallet with id " + walletId + " doesn't exist"));
    }

    public Wallet getWalletByUser(final User user) throws NotFoundException {
        return walletRepository.findByUser(user).orElseThrow(
                () -> new NotFoundException("Wallet of user " + user + " doesn't exist"));
    }

    public Wallet saveWallet(final Wallet wallet) throws WalletExsistsException {
        //if (!walletRepository.findByUser(wallet.getUser().getId()).isPresent()) {
        return walletRepository.save(wallet);
        //} else {
        //  throw new WalletExsistsException("Wallet for user with given id already exists, " +
        //         "please check your wallet id");
    }
    // }

    public void deleteWallet(final long walletId) throws WalletNotEmptyException {
        if (transactionRepository.findAllByWalletId(walletId).isEmpty()) {
            walletRepository.deleteById(walletId);
        } else {
            throw new WalletNotEmptyException("Please sell all shares before you delete your Wallet");
        }
    }
}

