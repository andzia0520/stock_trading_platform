package com.kodilla.stock_trading_platform.webClient.finnhub;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ShareFromFinnhubDto {
    private BigDecimal sharePrice;
}
