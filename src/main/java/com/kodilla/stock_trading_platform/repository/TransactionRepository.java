package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.Transaction;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    List<Transaction> findAllByTransactionType(TransactionType transactionType);

    @Override
    void deleteById(Long transactionId);

    @Override
    List<Transaction> findAll();
}
