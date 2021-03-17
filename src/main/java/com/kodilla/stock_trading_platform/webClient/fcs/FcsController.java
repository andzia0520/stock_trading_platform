package com.kodilla.stock_trading_platform.webClient.fcs;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/fcsApi")
public class FcsController {

    private final FcsService fcsService;

    @RequestMapping(method = RequestMethod.GET, value = "/shareSymbol/{companyName}")
    public ShareFromFcsDto getShareSymbol(@PathVariable String companyName) {
        return fcsService.getShareSymbolByName(companyName);
    }
}
