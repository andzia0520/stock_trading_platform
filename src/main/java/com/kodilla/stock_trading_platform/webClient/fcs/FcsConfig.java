package com.kodilla.stock_trading_platform.webClient.fcs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FcsConfig {
    @Value("${fcs.api.endpoint}")
    private String fcsApiEndpoint;

    @Value("${fcs.api.access_key}")
    private String fcsApiAccessKey;
}
