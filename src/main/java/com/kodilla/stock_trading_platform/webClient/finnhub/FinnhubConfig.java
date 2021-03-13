package com.kodilla.stock_trading_platform.webClient.finnhub;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FinnhubConfig {
    @Value("${finnhub.api.endpoint}")
    private String finnhubApiEndpoint;

    @Value("${finnhub.api.token}")
    private String finnhubApiToken;
}
