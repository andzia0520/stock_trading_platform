package com.kodilla.stock_trading_platform.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WALLETS")
public class Wallet {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "wallet",
            cascade = REMOVE)
    private List<Transaction> transactionsList;

    public Wallet(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wallet wallet = (Wallet) o;

        if (id != wallet.id) return false;
        if (!user.equals(wallet.user)) return false;
        return transactionsList.equals(wallet.transactionsList);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + transactionsList.hashCode();
        return result;
    }
}
