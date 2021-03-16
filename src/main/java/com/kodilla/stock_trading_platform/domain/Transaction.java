package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @NotNull
    @Column(name = "SHARE_SYMBOL")
    private String shareSymbol;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;

    @NotNull
    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;

    @NotNull
    @ManyToOne
    private Wallet wallet;

    public Transaction(TransactionType transactionType, String shareSymbol, BigDecimal price, int quantity, LocalDate transactionDate) {
        this.transactionType = transactionType;
        this.shareSymbol = shareSymbol;
        this.price = price;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }

    public Transaction(@NotNull long id, TransactionType transactionType, String shareSymbol, BigDecimal price, int quantity, LocalDate transactionDate) {
        this.id = id;
        this.transactionType = transactionType;
        this.shareSymbol = shareSymbol;
        this.price = price;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }

    public Transaction(TransactionType transactionType, String shareSymbol, BigDecimal price, int quantity, LocalDate transactionDate, Wallet wallet) {
        this.transactionType = transactionType;
        this.shareSymbol = shareSymbol;
        this.price = price;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
        this.wallet = wallet;
    }
}
