package com.kodilla.stock_trading_platform.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "SHARE_ID")
    private Share share;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;


    @ManyToOne
    private Wallet wallet;

    public Transaction(TransactionType transactionType, @NotNull Share share, int quantity, LocalDate transactionDate) {
        this.transactionType = transactionType;
        this.share = share;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }

    public Transaction(@NotNull long id, TransactionType transactionType, Share share, int quantity, LocalDate transactionDate) {
        this.id = id;
        this.transactionType = transactionType;
        this.share = share;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
    }

    public Transaction(TransactionType transactionType, Share share, int quantity, LocalDate transactionDate, Wallet wallet) {
        this.transactionType = transactionType;
        this.share = share;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (id != that.id) return false;
        if (quantity != that.quantity) return false;
        if (transactionType != that.transactionType) return false;
        if (!share.equals(that.share)) return false;
        return transactionDate.equals(that.transactionDate);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + transactionType.hashCode();
        result = 31 * result + share.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + transactionDate.hashCode();
        return result;
    }
}
