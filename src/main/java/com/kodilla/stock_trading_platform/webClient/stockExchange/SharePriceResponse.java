package com.kodilla.stock_trading_platform.webClient.stockExchange;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class SharePriceResponse {
    private BigDecimal sharePrice;
}
