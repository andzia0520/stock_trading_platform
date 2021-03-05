package com.kodilla.stock_trading_platform.webClient.stockExchange;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockExchangeService {

    @Autowired
    private final StockExchangeClient stockExchangeClient;

    public SharePriceResponse getSharePrice() {
        return stockExchangeClient.getPriceForShare("AAPL");
    }

    public ShareSymbolResponse getShareSymbolByName() {
        return stockExchangeClient.getSymbolByName("tesla");
    }
}
