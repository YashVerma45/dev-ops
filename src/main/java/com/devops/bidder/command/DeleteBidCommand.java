package com.devops.bidder.command;

import com.devops.bidder.service.BidService;
import org.springframework.stereotype.Component;

@Component
public class DeleteBidCommand {

    private final BidService bidService;

    public DeleteBidCommand(BidService bidService) {
        this.bidService = bidService;
    }

    public void execute(String itemId, String bidId) {
        bidService.removeBid(itemId, bidId);
    }
}
