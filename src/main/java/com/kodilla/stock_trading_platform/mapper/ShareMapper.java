package com.kodilla.stock_trading_platform.mapper;

import com.kodilla.stock_trading_platform.domain.Share;
import com.kodilla.stock_trading_platform.domain.ShareDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShareMapper {

    public Share mapToShare(final ShareDto shareDto) {
        return new Share(
                shareDto.getId(),
                shareDto.getCompanyName(),
                shareDto.getIndexName(),
                shareDto.getPrice());
    }

    public ShareDto mapToShareDto(final Share share) {
        return new ShareDto(
                share.getId(),
                share.getCompanyName(),
                share.getIndexName(),
                share.getPrice());
    }

    public List<ShareDto> mapToShareDtoList(final List<Share> shareList) {
        return shareList.stream()
                .map(share -> new ShareDto(share.getId(), share.getCompanyName(),
                        share.getIndexName(), share.getPrice()))
                .collect(Collectors.toList());
    }
}
