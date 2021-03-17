package com.kodilla.stock_trading_platform.webClient.finnhub;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/finnhubApi")
public class FinnhubController {

    private final FinnhubService finnhubService;

    @RequestMapping(method = RequestMethod.GET, value = "/sharePrice/{shareSymbol}")
    public ShareFromFinnhubDto getSharePrice(@PathVariable String shareSymbol) {
        return finnhubService.getSharePrice(shareSymbol);
    }
}
