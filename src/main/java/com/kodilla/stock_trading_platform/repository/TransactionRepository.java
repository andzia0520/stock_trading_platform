package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.Transaction;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Override
    Transaction save(Transaction transaction);

    @Override
    Optional<Transaction> findById(Long transactionId);

    List<Transaction> findAllByWalletId(Long walletId);

    List<Transaction> findAllByWalletIdAndTransactionType(Long walletId, TransactionType transactionType);

    List<Transaction> findAllByWalletIdAndShareSymbol(Long walletId, String shareSymbol);

    List<Transaction> findAllByWalletIdAndTransactionDate(Long walletId, LocalDate transactionDate);

    void deleteAllByWalletId(Long walletId);

    @Override
    List<Transaction> findAll();
}

