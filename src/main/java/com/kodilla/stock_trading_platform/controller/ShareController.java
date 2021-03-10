package com.kodilla.stock_trading_platform.controller;

import com.kodilla.stock_trading_platform.domain.ShareDto;
import com.kodilla.stock_trading_platform.mapper.ShareMapper;
import com.kodilla.stock_trading_platform.service.ShareDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/share")
public class ShareController {
    @Autowired
    private ShareDbService shareDbService;

    @Autowired
    private ShareMapper shareMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getShares")
    public List<ShareDto> getShares() {
        return shareMapper.mapToShareDtoList(shareDbService.getShares());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getShareById")
    public ShareDto getShareById(@RequestParam long shareId) throws ShareNotFoundException {
        return shareMapper.mapToShareDto(shareDbService.getShareById(shareId).orElseThrow(ShareNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getShareByCompanyName")
    public ShareDto getShareByCompanyName(@RequestParam String companyName) throws ShareNotFoundException {
        return shareMapper.mapToShareDto(shareDbService.getShareByCompanyName(companyName).orElseThrow(ShareNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getShareByShareSymbol")
    public ShareDto getShareByShareSymbol(@RequestParam String shareSymbol) throws ShareNotFoundException {
        return shareMapper.mapToShareDto(shareDbService.getShareByShareSymbol(shareSymbol).orElseThrow(ShareNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createShare")
    public void createShare(@RequestBody ShareDto shareDto) {
        shareDbService.saveShare(shareMapper.mapToShare(shareDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteShare")
    public void deleteShare(@RequestParam long shareId) {
        shareDbService.deleteShareById(shareId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateShare")
    public ShareDto updateShare(@RequestBody ShareDto shareDto) {
        return shareMapper.mapToShareDto(shareDbService.saveShare(shareMapper.mapToShare(shareDto)));
    }
}
