package com.kodilla.stock_trading_platform.webClient.stockExchange;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShareSymbolResponse {
    private String symbol;
}
