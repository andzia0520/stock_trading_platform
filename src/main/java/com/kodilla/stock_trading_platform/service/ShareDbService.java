package com.kodilla.stock_trading_platform.service;

import com.kodilla.stock_trading_platform.domain.Share;
import com.kodilla.stock_trading_platform.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShareDbService {
    @Autowired
    private ShareRepository shareRepository;

    public List<Share> getShares() {
        return shareRepository.findAll();
    }

    public Optional<Share> getShareById(final long shareId) {
        return shareRepository.findById(shareId);
    }

    public Optional<Share> getShareByCompanyName(final String companyName) {
        return shareRepository.findByCompanyName(companyName);
    }

    public Optional<Share> getShareByShareSymbol(final String shareSymbol) {
        return shareRepository.findByShareSymbol(shareSymbol);
    }

    public Share saveShare(final Share share) {
        return shareRepository.save(share);
    }

    public void deleteShareById(final long shareId) {
        shareRepository.deleteById(shareId);
    }
}
