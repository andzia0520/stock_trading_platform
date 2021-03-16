package com.kodilla.stock_trading_platform.service;

public class UserAlreadyExistException extends Exception{
    public UserAlreadyExistException(String s) {
        super(s);
    }
}
