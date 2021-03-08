package com.kodilla.stock_trading_platform.webClient.stockExchange;

//import com.kodilla.stock_trading_platform.externalData.stockExchangeApi.client.StockExchangeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stockApi")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    @RequestMapping(method = RequestMethod.GET, value = "getSharePrice")
    public SharePriceResponse getSharePrice() {
        return stockExchangeService.getSharePrice();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getShareSymbol")
    public ShareSymbolResponse getShareSymbol() {
        return stockExchangeService.getShareSymbolByName();
    }
}