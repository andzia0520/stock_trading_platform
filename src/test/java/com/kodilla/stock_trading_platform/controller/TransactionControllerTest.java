package com.kodilla.stock_trading_platform.controller;

import com.google.gson.Gson;
import com.kodilla.stock_trading_platform.domain.*;
import com.kodilla.stock_trading_platform.mapper.TransactionMapper;
import com.kodilla.stock_trading_platform.mapper.UserMapper;
import com.kodilla.stock_trading_platform.service.TransactionDbService;
import com.kodilla.stock_trading_platform.service.UserAlreadyExistException;
import com.kodilla.stock_trading_platform.service.UserDbService;
import com.kodilla.stock_trading_platform.service.WalletDbService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionDbService transactionDbService;

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private WalletDbService walletDbService;

    @MockBean
    private TransactionMapper transactionMapper;

    @Test
    public void shouldCreateTransaction() throws Exception {
        //Given
        User user = new User(1L, "testUser", "test@test.pl");
        userDbService.saveUser(user);
        Wallet wallet = new Wallet(2, user);
        walletDbService.saveWallet(wallet);
        Transaction transaction = new Transaction(1L, TransactionType.BUY, "AMZN", new BigDecimal(234),
                235, LocalDate.of(2021, 03, 17), wallet);
        TransactionDto transactionDto = new TransactionDto(1L, TransactionType.BUY, "AMZN", new BigDecimal(234),
                235, LocalDate.of(2021, 03, 17), 2L);

        when(transactionDbService.saveTransaction(transactionMapper.mapToTransaction(transactionDto))).thenReturn(transaction);
        when(transactionMapper.mapToTransactionDto(transaction)).thenReturn(transactionDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(transactionDto);

        //When & Then
        mockMvc.perform(post("/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }
}
