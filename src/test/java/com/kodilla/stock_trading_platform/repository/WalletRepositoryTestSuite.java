package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.Transaction;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.Wallet;
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
    private TransactionRepository transactionRepository;

    @Test
    public void testWalletSaveAndFindAll() {
        //Given
        User hossaUser = new User("hossa", "hossa@hossa.pl");
        User bessaUser = new User("bessa", "bessa@bessa.pl");
        userRepository.save(hossaUser);
        userRepository.save(bessaUser);
        Wallet wallet1 = new Wallet(hossaUser);
        Wallet wallet2 = new Wallet(bessaUser);

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
        userRepository.deleteById(hossaUser.getId());
        userRepository.deleteById(bessaUser.getId());
    }

    @Test
    public void testWalletDeleteByIdWithoutUser() {
        //Given
        User hossaUser = new User("hossa", "hossa@hossa.pl");
        userRepository.save(hossaUser);
        Wallet hossaWallet = new Wallet(hossaUser);
        hossaWallet.setUser(hossaUser);
        walletRepository.save(hossaWallet);

        //When
        walletRepository.deleteById(hossaWallet.getId());

        //Then
        Optional<Wallet> checkWallet = walletRepository.findById(hossaWallet.getId());
        Optional<User> checkUser = userRepository.findById(hossaUser.getId());
        assertFalse(checkWallet.isPresent());
        assertTrue(checkUser.isPresent());

        //CleanUp
        userRepository.deleteById(hossaUser.getId());
    }

    @Test
    public void testWalletDeleteWithTransactions() {
        //Given
        User hossaUser = new User("hossa", "hossa@hossa.pl");
        userRepository.save(hossaUser);

        Wallet wallet = new Wallet(hossaUser);
        walletRepository.save(wallet);

        Transaction transaction1 = new Transaction(TransactionType.BUY, "AMZN",
                new BigDecimal(245), 1000, LocalDate.now(), wallet);
        Transaction transaction2 = new Transaction(TransactionType.SELL, "AMZN",
                new BigDecimal(245), 1000, LocalDate.now(), wallet);
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
        transactionRepository.deleteAll();
        walletRepository.deleteAll();
        userRepository.deleteById(hossaUser.getId());
    }
}

