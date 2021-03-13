package com.kodilla.stock_trading_platform.webClient.finnhub;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/finnhubApi")
public class FinnhubController {

    private final FinnhubService finnhubService;

    @RequestMapping(method = RequestMethod.GET, value = "getSharePrice")
    public ShareFromFinnhubDto getSharePrice(@RequestParam String shareSymbol) {
        return finnhubService.getSharePrice(shareSymbol);
    }
}
