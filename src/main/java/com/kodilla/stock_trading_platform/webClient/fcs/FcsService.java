package com.kodilla.stock_trading_platform.webClient.fcs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FcsService {

    @Autowired
    private final FcsClient fcsClient;

    public ShareFromFcsDto getShareSymbolByName(String companyName) {
        return fcsClient.getShareSymbolByName(companyName);
    }
}
