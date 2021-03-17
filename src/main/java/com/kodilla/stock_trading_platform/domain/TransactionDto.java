package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class TransactionDto {
    private long id;
    private TransactionType transactionType;
    private String shareSymbol;
    private BigDecimal price;
    private int quantity;
    private LocalDate transactionDate;
    private Long walletId;


    public TransactionDto(Long walletId, TransactionType transactionType, String shareSymbol, BigDecimal price,
                          int quantity, LocalDate transactionDate) {

        this.walletId = walletId;
        this.transactionType = transactionType;
        this.shareSymbol = shareSymbol;
        this.price = price;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }
}

