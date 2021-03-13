package com.kodilla.stock_trading_platform.webClient.marketstack;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/fcsApi")
public class FcsController {

    private final FcsService fcsService;

    @RequestMapping(method = RequestMethod.GET, value = "getShareSymbol")
    public ShareFromFcsDto getShareSymbol(@RequestParam String companyName) {
        return fcsService.getShareSymbolByName(companyName);
    }
}
