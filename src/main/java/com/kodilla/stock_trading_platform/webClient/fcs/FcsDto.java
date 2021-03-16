package com.kodilla.stock_trading_platform.webClient.fcs;

import lombok.Getter;

import java.util.List;

@Getter
public class FcsDto {
    private List<ResponseDto> response;
}