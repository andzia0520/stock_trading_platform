package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WalletRepositoryTestSuite {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testShouldSaveWalletAndFindById() {
        //Given
        User hossa = new User("hossa");
        User bessa = new User("bessa");
        userRepository.save(hossa);
        userRepository.save(bessa);

        Wallet wallet1 = new Wallet(hossa);
        Wallet wallet2 = new Wallet(bessa);

        //When
        walletRepository.save(wallet1);
        walletRepository.save(wallet2);
        Optional<Wallet> result1 = walletRepository.findById(wallet1.getId());
        Optional<Wallet> result2 = walletRepository.findById(wallet2.getId());

        //Then
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());

        //CleanUp
        for (Wallet input : walletRepository.findAll()) {
            walletRepository.deleteById(input.getId());
        }
        userRepository.deleteAll();
    }

    @Test
    public void testShouldDeleteWalletWithoutUser() {
        //Given
        User user = new User("user");
        userRepository.save(user);
        Wallet hossaWallet = new Wallet(user);
        walletRepository.save(hossaWallet);

        //When
        walletRepository.deleteById(hossaWallet.getId());

        //Then
        Optional<Wallet> checkWallet = walletRepository.findById(hossaWallet.getId());
        Optional<User> checkUser = userRepository.findById(user.getId());
        assertFalse(checkWallet.isPresent());
        assertTrue(checkUser.isPresent());

        //CleanUp
        //userRepository.deleteById(user.getId());
    }

    @Test
    public void testShouldDeleteWalletWithTransactions() {
        //Given
        Share amazonShare = new Share("Amazon", "AMZN", new BigDecimal(2200));
        shareRepository.save(amazonShare);
        User user = new User("user");
        userRepository.save(user);
        Wallet wallet = new Wallet(user);
        walletRepository.save(wallet);

        Transaction transaction1 = new Transaction(TransactionType.SELL, amazonShare, 1000, LocalDate.now(), wallet);
        Transaction transaction2 = new Transaction(TransactionType.BUY, amazonShare, 35, LocalDate.now(), wallet);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        //When
        List<Wallet> walletsBeforeDelete = walletRepository.findAll();
        List<Transaction> transactionsBeforeDelete = transactionRepository.findAll();
        walletRepository.deleteById(wallet.getId());
        List<Wallet> walletsAfterDelete = walletRepository.findAll();
        List<Transaction> transactionsAfterDelete = transactionRepository.findAll();

        //Then
        assertTrue(walletsBeforeDelete.size() > walletsAfterDelete.size());
        assertTrue(transactionsBeforeDelete.size() > transactionsAfterDelete.size());

        //CleanUp
        shareRepository.deleteById(amazonShare.getId());
        userRepository.deleteById(user.getId());
    }
}
