package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface WalletRepository extends CrudRepository<Wallet, Long> {
    @Override
    Wallet save(Wallet wallet);

    @Override
    List<Wallet> findAll();

    @Override
    Optional<Wallet> findById(Long id);

    Optional<Wallet> findByUserId(Long userId);

    @Override
    void deleteById(Long walletId);

}
