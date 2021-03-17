package com.kodilla.stock_trading_platform.service;

public class WalletExsistsException extends Exception {
    public WalletExsistsException(String message) {
        super(message);
    }
}
