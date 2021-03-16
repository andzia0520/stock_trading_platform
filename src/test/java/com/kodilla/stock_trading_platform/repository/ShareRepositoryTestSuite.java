/*
package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.Share;
import com.kodilla.stock_trading_platform.domain.Transaction;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ShareRepositoryTestSuite {

    @Autowired
    ShareRepository shareRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void testShouldSaveShare() {
        //Given
        Share shareAmzn = new Share("Amazon", "AMZN", new BigDecimal("3328.23"));
        Share shareTsla = new Share("Tesla", "TSLA", new BigDecimal("787.37"));
        Share shareGoogl = new Share("Google", "GOOGL", new BigDecimal("2105.37"));
        shareRepository.save(shareAmzn);
        shareRepository.save(shareTsla);

        //When
        List<Share> sharesList = shareRepository.findAll();
        Optional<Share> checkAmzn = shareRepository.findById(shareAmzn.getId());
        Optional<Share> checkTsla = shareRepository.findById(shareTsla.getId());
        Optional<Share> checkGoogl = shareRepository.findById(shareGoogl.getId());

        //Then
        assertTrue(checkAmzn.isPresent());
        assertTrue(checkTsla.isPresent());
        assertFalse(checkGoogl.isPresent());

        //CleanUp
        shareRepository.deleteById(shareAmzn.getId());
        shareRepository.deleteById(shareTsla.getId());
    }

    @Test
    public void testShouldDeleteShareWithTransactions() {
        //Given
        Share amazonShare = new Share("Amazon", "AMZN", new BigDecimal(2200));
        shareRepository.save(amazonShare);
        Transaction transaction1 = new Transaction(TransactionType.SELL, amazonShare, 1000, LocalDate.now());
        Transaction transaction2 = new Transaction(TransactionType.BUY, amazonShare, 20, LocalDate.now());
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        amazonShare.getTransactions().add(transaction1);
        amazonShare.getTransactions().add(transaction2);

        //When
        shareRepository.deleteById(amazonShare.getId());

        //Then
        assertFalse(shareRepository.findById(amazonShare.getId()).isPresent());
        assertFalse(transactionRepository.findById(transaction1.getId()).isPresent());
        assertFalse(transactionRepository.findById(transaction2.getId()).isPresent());
    }

}
*/
