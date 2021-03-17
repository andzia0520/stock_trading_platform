package com.kodilla.stock_trading_platform.mapper;

import com.kodilla.stock_trading_platform.domain.Transaction;
import com.kodilla.stock_trading_platform.domain.TransactionDto;
import com.kodilla.stock_trading_platform.service.WalletDbService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    @Autowired
    private WalletDbService walletDbService;

    public Transaction mapToTransaction(final TransactionDto transactionDto) throws NotFoundException {
        return new Transaction(
                transactionDto.getId(),
                transactionDto.getTransactionType(),
                transactionDto.getShareSymbol(),
                transactionDto.getPrice(),
                transactionDto.getQuantity(),
                transactionDto.getTransactionDate(),
                walletDbService.getWalletById(transactionDto.getWalletId()));
    }

    public TransactionDto mapToTransactionDto(final Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getTransactionType(),
                transaction.getShareSymbol(),
                transaction.getPrice(),
                transaction.getQuantity(),
                transaction.getTransactionDate(),
                transaction.getWallet().getId());
    }

    public List<Transaction> mapToTransactionList(final List<TransactionDto> transactionDtoList) {
        return transactionDtoList.stream()
                .map(transactionDto -> new Transaction(transactionDto.getId(), transactionDto.getTransactionType(),
                        transactionDto.getShareSymbol(), transactionDto.getPrice(), transactionDto.getQuantity(), transactionDto.getTransactionDate()))
                .collect(Collectors.toList());
    }

    public List<TransactionDto> mapToTransactionDtoList(final List<Transaction> transactionList) {
        return transactionList.stream()
                .map(transaction -> new TransactionDto(transaction.getId(), transaction.getTransactionType(),
                        transaction.getShareSymbol(), transaction.getPrice(), transaction.getQuantity(),
                        transaction.getTransactionDate(), transaction.getWallet().getId()))
                .collect(Collectors.toList());
    }
}

