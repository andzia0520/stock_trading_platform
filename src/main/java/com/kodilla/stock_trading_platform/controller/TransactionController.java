package com.kodilla.stock_trading_platform.controller;

import com.kodilla.stock_trading_platform.domain.TransactionDto;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import com.kodilla.stock_trading_platform.mapper.TransactionMapper;
import com.kodilla.stock_trading_platform.service.TransactionDbService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TransactionController {

    @Autowired
    private TransactionDbService transactionDbService;

    @Autowired
    private TransactionMapper transactionMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/transactions")
    public void createTransaction(@RequestBody TransactionDto transactionDto) throws NotFoundException {
        transactionDbService.saveTransaction(transactionMapper.mapToTransaction(transactionDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/transactions/{walletId}")
    public void deleteTransactions(@PathVariable Long walletId) {
        transactionDbService.deleteTransactions(walletId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transactions/{walletId}")
    public List<TransactionDto> getTransactionsByWalletId(@PathVariable Long walletId) {
        return transactionMapper.mapToTransactionDtoList(transactionDbService.getTransactionByWalletId(walletId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transactions/{walletId, transactionType}")
    public List<TransactionDto> getTransactionByType(@PathVariable Long walletId, TransactionType transactionType) {
        return transactionMapper.mapToTransactionDtoList(transactionDbService.getTransactionByWalletIdAndType(walletId,transactionType));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/transactions/{walletId, shareSymbol}")
    public List<TransactionDto> getTransactionByShareSymbol(@PathVariable Long walletId, String shareSymbol) {
        return transactionMapper.mapToTransactionDtoList(transactionDbService.getTransactionByWalletIdAndShareSymbol(walletId, shareSymbol));
    }


}

