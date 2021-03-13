package com.kodilla.stock_trading_platform.webClient.marketstack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FcsClient {

    @Autowired
    private FcsConfig fcsConfig;

    @Autowired
    private RestTemplate restTemplate;

    public ShareFromFcsDto getShareSymbolByName(String companyName) {
        FcsDto fcsDto = callGetMethod("/stock/search?s={companyName}&type=stock&access_key=", FcsDto.class, companyName);
        return ShareFromFcsDto.builder()
                .shareSymbol(fcsDto.getResponse().get(0).getShort_name())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(fcsConfig.getFcsApiEndpoint()
                        + url + fcsConfig.getFcsApiAccessKey(),
                responseType, objects);
    }
}
