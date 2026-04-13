package com.devops.bidder.command;

import com.devops.bidder.domain.Bid;
import com.devops.bidder.service.BidService;
import com.devops.bidder.web.dto.AddBidRequest;
import org.springframework.stereotype.Component;

@Component
public class AddBidCommand {

    private final BidService bidService;

    public AddBidCommand(BidService bidService) {
        this.bidService = bidService;
    }

    public Bid execute(String itemId, AddBidRequest request) {
        return bidService.addBid(itemId, request);
    }
}
