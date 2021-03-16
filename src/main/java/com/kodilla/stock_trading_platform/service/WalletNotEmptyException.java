package com.kodilla.stock_trading_platform.service;

public class WalletNotEmptyException extends Exception {
    public WalletNotEmptyException(String message) {
        super(message);
    }
}
