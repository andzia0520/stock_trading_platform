package com.kodilla.stock_trading_platform.service;

import com.kodilla.stock_trading_platform.domain.Transaction;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import com.kodilla.stock_trading_platform.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionDbService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(final Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(final long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionByWalletId(final long walletId) {
        return transactionRepository.findAllByWalletId(walletId);
    }

    public List<Transaction> getTransactionByType(final TransactionType transactionType) {
        return transactionRepository.findAllByTransactionType(transactionType);
    }

    public void deleteTransaction(final long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
