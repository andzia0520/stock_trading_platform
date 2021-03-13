package com.kodilla.stock_trading_platform.webClient.finnhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FinnhubClient {

    @Autowired
    private FinnhubConfig finnhubConfig;

    @Autowired
    private RestTemplate restTemplate;

    public ShareFromFinnhubDto getPriceForShare(String shareSymbol) {
        FinnhubDto finnhubDto = callGetMethod("/quote?symbol={shareSymbol}&token=", FinnhubDto.class, shareSymbol);
    return ShareFromFinnhubDto.builder()
            .sharePrice(finnhubDto.getC())
            .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(finnhubConfig.getFinnhubApiEndpoint()
                        + url + finnhubConfig.getFinnhubApiToken(),
                responseType, objects );
    }
}
