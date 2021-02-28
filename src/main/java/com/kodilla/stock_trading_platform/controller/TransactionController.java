package com.kodilla.stock_trading_platform.controller;

import com.kodilla.stock_trading_platform.domain.TransactionDto;
import com.kodilla.stock_trading_platform.domain.TransactionType;
import com.kodilla.stock_trading_platform.mapper.TransactionMapper;
import com.kodilla.stock_trading_platform.service.TransactionDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionDbService transactionDbService;

    @Autowired
    private TransactionMapper transactionMapper;

    @RequestMapping(method = RequestMethod.POST, value = "createTransaction")
    public void createTransaction(@RequestBody TransactionDto transactionDto) {
        transactionDbService.saveTransaction(transactionMapper.mapToTransaction(transactionDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTransaction")
    public void deleteTransaction(@RequestParam long transactionId) {
        transactionDbService.deleteTransaction(transactionId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTransactions")
    public List<TransactionDto> getTransactions() {
        return transactionMapper.mapToTransactionDtoList(transactionDbService.getTransactions());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTransactionsByWalletId")
    public List<TransactionDto> getTransactions(@RequestParam long walletId) {
        return transactionMapper.mapToTransactionDtoList(transactionDbService.getTransactionByWalletId(walletId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTransactionById")
    public TransactionDto getTransactionById(@RequestParam long transactionId) throws TransactionNotFoundException {
        return transactionMapper.mapToTransactionDto(transactionDbService.getTransactionById(transactionId).orElseThrow(TransactionNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTransactionByType")
    public List<TransactionDto> getTransactionByType(@RequestParam TransactionType transactionType) {
        return transactionMapper.mapToTransactionDtoList(transactionDbService.getTransactionByType(transactionType));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTransaction")
    public TransactionDto updateTransaction(@RequestBody TransactionDto transactionDto) {
        return transactionMapper.mapToTransactionDto(transactionDbService.saveTransaction(transactionMapper.mapToTransaction(transactionDto)));
    }
}
