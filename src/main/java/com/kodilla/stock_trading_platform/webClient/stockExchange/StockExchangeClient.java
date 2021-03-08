
package com.kodilla.stock_trading_platform.webClient.stockExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StockExchangeClient {

    @Autowired
    private StockExchangeConfig stockExchangeConfig;

    @Autowired
    private RestTemplate restTemplate;

    public SharePriceResponse getPriceForShare(String shareSymbol) {
        SharePriceDto sharePriceDto = callGetMethod("/quote?symbol={shareSymbol}&token=", SharePriceDto.class, shareSymbol);
    return SharePriceResponse.builder()
            .sharePrice(sharePriceDto.getC())
            .build();
    }

    public ShareSymbolResponse getSymbolByName(String name) {
        ShareSymbolDto shareSymbolDto = callGetMethod("/search?q={name}&token=", ShareSymbolDto.class, name);
        return ShareSymbolResponse.builder()
                .symbol(shareSymbolDto.getResult().get(0).getSymbol())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(stockExchangeConfig.getStockExchangeApiEndpoint()
                        + url + stockExchangeConfig.getStockExchangeApiToken(),
                responseType, objects );
    }

/*    private URI getUrl() {
        return UriComponentsBuilder.fromHttpUrl(stockExchangeApiConfig.getStockExchangeApiEndpoint() + "/reference/tickers")
                .queryParam("market", "stocks")
                .queryParam("locale", "us")
                .queryParam("apiKey", stockExchangeApiConfig.getStockExchangeApiKey())
                .build().encode().toUri();
    }

    public ShareFromApiDto getShares() {
        PollygonDto pollygonDto = restTemplate.getForObject(getUrl(), PollygonDto.class);
        return ShareFromApiDto.builder()
                .shareSymbol(pollygonDto.getShares().keySet().toString())
                .companyName(pollygonDto.getShares().values().toString())
                .build();
    }*/
}
