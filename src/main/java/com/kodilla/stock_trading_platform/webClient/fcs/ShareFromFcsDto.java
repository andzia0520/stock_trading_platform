package com.kodilla.stock_trading_platform.webClient.fcs;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShareFromFcsDto {
    private String shareSymbol;

    @Override
    public String toString() {
        return shareSymbol;
    }
}
