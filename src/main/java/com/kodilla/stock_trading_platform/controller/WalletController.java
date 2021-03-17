package com.kodilla.stock_trading_platform.controller;

import com.kodilla.stock_trading_platform.domain.User;
import com.kodilla.stock_trading_platform.domain.WalletDto;
import com.kodilla.stock_trading_platform.mapper.WalletMapper;
import com.kodilla.stock_trading_platform.service.WalletDbService;
import com.kodilla.stock_trading_platform.service.WalletExsistsException;
import com.kodilla.stock_trading_platform.service.WalletNotEmptyException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class WalletController {

    @Autowired
    private WalletDbService walletDbService;

    @Autowired
    private WalletMapper walletMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/wallets")
    public WalletDto createWallet(@RequestBody WalletDto walletDto) throws WalletExsistsException, NotFoundException {
        return walletMapper.mapToWalletDto((walletDbService.saveWallet(walletMapper.mapToWallet(walletDto))));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/wallets/{walletId}")
    public void deleteWallet(@PathVariable Long walletId) throws WalletNotEmptyException {
        walletDbService.deleteWallet(walletId);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/wallets/{walletId}")
    public WalletDto getWallet(@PathVariable Long id) throws NotFoundException {
        return walletMapper.mapToWalletDto(walletDbService.getWalletById(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/wallets/{user}")
    public WalletDto getWalletByUser(@PathVariable User user) throws NotFoundException {
        return walletMapper.mapToWalletDto(walletDbService.getWalletByUser(user));
    }
}


