package com.kodilla.stock_trading_platform.mapper;

import com.kodilla.stock_trading_platform.domain.Transaction;
import com.kodilla.stock_trading_platform.domain.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private WalletMapper walletMapper;

    public Transaction mapToTransaction(final TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.getId(),
                transactionDto.getTransactionType(),
                shareMapper.mapToShare(transactionDto.getShareDto()),
                transactionDto.getQuantity(),
                transactionDto.getTransactionDate(),
                walletMapper.mapToWallet(transactionDto.getWalletDto()));
    }

    public TransactionDto mapToTransactionDto(final Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getTransactionType(),
                shareMapper.mapToShareDto(transaction.getShare()),
                transaction.getQuantity(),
                transaction.getTransactionDate(),
                walletMapper.mapToWalletDto(transaction.getWallet()));
    }

    public List<Transaction> mapToTransactionList(final List<TransactionDto> transactionDtoList) {
        return transactionDtoList.stream()
                .map(transactionDto -> new Transaction(transactionDto.getId(), transactionDto.getTransactionType(),
                        shareMapper.mapToShare(transactionDto.getShareDto()), transactionDto.getQuantity(), transactionDto.getTransactionDate()))
                .collect(Collectors.toList());
    }

    public List<TransactionDto> mapToTransactionDtoList(final List<Transaction> transactionList) {
        return transactionList.stream()
                .map(transaction -> new TransactionDto(transaction.getId(), transaction.getTransactionType(),
                        shareMapper.mapToShareDto(transaction.getShare()), transaction.getQuantity(),
                        transaction.getTransactionDate(),walletMapper.mapToWalletDto(transaction.getWallet())))
                .collect(Collectors.toList());

    }
}
