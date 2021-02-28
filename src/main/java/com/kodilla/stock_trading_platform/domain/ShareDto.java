package com.kodilla.stock_trading_platform.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ShareDto {
    private long id;
    private String companyName;
    private String indexName;
    private BigDecimal price;
}
