package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TransactionRepositoryTestSuite {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testShouldDeleteTransactionWithoutWalletAndShare() {

        //Given
        Share amazonShare = new Share("Amazon", "AMZN", new BigDecimal(2200));
        shareRepository.save(amazonShare);

        User user = new User("user", "user@gmail.com");
        userRepository.save(user);

        Wallet wallet = new Wallet(user);
        walletRepository.save(wallet);

        Transaction transaction1 = new Transaction(TransactionType.SELL, amazonShare, 1000, LocalDate.now(), wallet);
        Transaction transaction2 = new Transaction(TransactionType.BUY, amazonShare, 35, LocalDate.now(), wallet);

        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);

        //When
        List<Share> sharesBeforeDelete = shareRepository.findAll();
        List<Wallet> walletsBeforeDelete = walletRepository.findAll();
        List<Transaction> transactionsBeforeDelete = transactionRepository.findAll();
        transactionRepository.deleteById(transaction1.getId());
        transactionRepository.deleteById(transaction2.getId());
        List<Share> sharesAfterDelete = shareRepository.findAll();
        List<Wallet> walletsAfterDelete = walletRepository.findAll();
        List<Transaction> transactionsAfterDelete = transactionRepository.findAll();

        //Then
        assertTrue(transactionsBeforeDelete.size() > transactionsAfterDelete.size());
        assertEquals(walletsBeforeDelete.size(), walletsAfterDelete.size());
        assertEquals(sharesBeforeDelete.size(), sharesAfterDelete.size());

        //CleanUp
        shareRepository.deleteAll();
        walletRepository.deleteAll();
        userRepository.deleteAll();
    }
}
