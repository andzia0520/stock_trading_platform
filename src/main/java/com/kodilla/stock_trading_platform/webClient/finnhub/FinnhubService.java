package com.kodilla.stock_trading_platform.webClient.finnhub;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinnhubService {

    @Autowired
    private final FinnhubClient finnhubClient;

    public ShareFromFinnhubDto getSharePrice(String shareSymbol) {
        return finnhubClient.getPriceForShare(shareSymbol);
    }
}
