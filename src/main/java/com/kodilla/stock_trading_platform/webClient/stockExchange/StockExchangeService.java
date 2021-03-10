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

    public SharePriceResponse getSharePrice(String shareSymbol) {
        return stockExchangeClient.getPriceForShare(shareSymbol);
    }

    public ShareSymbolResponse getShareSymbolByName(String companyName) {
        return stockExchangeClient.getSymbolByName(companyName);
    }
}
