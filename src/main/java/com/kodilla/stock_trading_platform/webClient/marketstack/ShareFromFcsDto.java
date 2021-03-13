package com.kodilla.stock_trading_platform.webClient.marketstack;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ShareFromFcsDto {
    private String shareSymbol;
}
