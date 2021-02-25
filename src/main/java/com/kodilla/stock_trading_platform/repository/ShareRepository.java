package com.kodilla.stock_trading_platform.repository;

import com.kodilla.stock_trading_platform.domain.Share;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ShareRepository extends CrudRepository<Share, Long> {
    @Override
    Share save(Share share);

    @Override
    List<Share> findAll();

    @Override
    Optional<Share> findById(Long Id);

    Optional<Share> findByCompanyName(String companyName);

    List<Share> findAllByIndexName(String indexName);

    @Override
    void deleteById(Long shareId);
}
