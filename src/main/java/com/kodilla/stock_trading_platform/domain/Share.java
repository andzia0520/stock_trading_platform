package com.kodilla.stock_trading_platform.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "SHARES")
public class Share {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "SHARE_SYMBOL")
    private String shareSymbol;

    @Column(name = "PRICE")
    private BigDecimal price;

    @OneToMany(
            targetEntity = Transaction.class,
            mappedBy = "share",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Share(String companyName, String shareSymbol, BigDecimal price) {
        this.companyName = companyName;
        this.shareSymbol = shareSymbol;
        this.price = price;
    }

    public Share(@NotNull long id, String companyName, String shareSymbol, BigDecimal price) {
        this.id = id;
        this.companyName = companyName;
        this.shareSymbol = shareSymbol;
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Share share = (Share) o;

        if (id != share.id) return false;
        if (!companyName.equals(share.companyName)) return false;
        if (!shareSymbol.equals(share.shareSymbol)) return false;
        return price.equals(share.price);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + companyName.hashCode();
        result = 31 * result + shareSymbol.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
