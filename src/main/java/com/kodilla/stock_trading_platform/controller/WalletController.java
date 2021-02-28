package com.kodilla.stock_trading_platform.controller;

import com.kodilla.stock_trading_platform.domain.WalletDto;
import com.kodilla.stock_trading_platform.mapper.WalletMapper;
import com.kodilla.stock_trading_platform.service.WalletDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/wallet")
public class WalletController {

    @Autowired
    private WalletDbService walletDbService;

    @Autowired
    private WalletMapper walletMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createWallet")
    public WalletDto createWallet(@RequestBody WalletDto walletDto) {
        return walletMapper.mapToWalletDto((walletDbService.saveWallet(walletMapper.mapToWallet(walletDto))));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getWallet")
    public WalletDto getWallet(@RequestParam Long walletId) throws WalletNotFoundException {
        return walletMapper.mapToWalletDto(walletDbService.getWalletById(walletId).orElseThrow(WalletNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteWallet")
    public void deleteWallet(@RequestParam Long walletId) {
        walletDbService.deleteWallet(walletId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateWallet")
    public void updateWallet(@RequestBody WalletDto walletDto) {
        walletDbService.saveWallet(walletMapper.mapToWallet(walletDto));
    }
}

