package com.kodilla.stock_trading_platform.service;

import com.kodilla.stock_trading_platform.domain.Transaction;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import com.kodilla.stock_trading_platform.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionDbService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void saveTransaction(final Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionByWalletId(final long walletId) {
        return transactionRepository.findAllByWalletId(walletId);
    }

    public List<Transaction> getTransactionByWalletIdAndType(final long walletId, final TransactionType transactionType) {
        return transactionRepository.findAllByWalletIdAndTransactionType(walletId, transactionType);
    }

    public List<Transaction> getTransactionByWalletIdAndShareSymbol(final long walletId, final String shareSymbol) {
        return transactionRepository.findAllByWalletIdAndShareSymbol(walletId, shareSymbol);
    }

    public List<Transaction> getTransactionByWalletIdAndTransactionDate(final long walletId, final LocalDate transactionDate) {
        return transactionRepository.findAllByWalletIdAndTransactionDate(walletId, transactionDate);
    }

    public void deleteTransactions(final long walletId) {
        transactionRepository.deleteAllByWalletId(walletId);
    }
}



