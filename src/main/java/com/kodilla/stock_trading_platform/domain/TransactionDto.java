package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class TransactionDto {
    private long id;
    private Long walletId;
    private TransactionType transactionType;
    private String shareSymbol;
    private BigDecimal price;
    private int quantity;
    private LocalDate transactionDate;


    public TransactionDto(Long walletId,TransactionType transactionType, String shareSymbol, BigDecimal price,
                          int quantity, LocalDate transactionDate) {

        this.walletId = walletId;
        this.transactionType = transactionType;
        this.shareSymbol = shareSymbol;
        this.price = price;
        this.quantity = quantity;
        this.transactionDate = transactionDate;

    }
}
