package com.kodilla.stock_trading_platform.domain;

import java.time.LocalDate;

public class Transaction {
    private long id;
    private TransactionType transactionType;
    private Share share;
    private int quantity;
    private LocalDate transactionDate;
}
