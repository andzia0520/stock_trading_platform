package com.kodilla.stock_trading_platform.webClient.stockExchange;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class StockExchangeConfig {
    @Value("${stockexchange.api.endpoint}")
    private String stockExchangeApiEndpoint;

    @Value("${stockexchange.api.token}")
    private String stockExchangeApiToken;
}
