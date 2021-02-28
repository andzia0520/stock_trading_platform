package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TransactionDto {
    private long id;
    private TransactionType transactionType;
    private ShareDto shareDto;
    private int quantity;
    private LocalDate transactionDate;
    private WalletDto walletDto;
}
