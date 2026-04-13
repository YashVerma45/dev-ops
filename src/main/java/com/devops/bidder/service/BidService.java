package com.devops.bidder.service;

import com.devops.bidder.domain.Bid;
import com.devops.bidder.repository.BidRepository;
import com.devops.bidder.web.dto.AddBidRequest;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class BidService {

    private final BidRepository bidRepository;

    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    public Bid addBid(String itemId, AddBidRequest request) {
        Bid bid = new Bid();
        bid.setId(UUID.randomUUID().toString());
        bid.setItemId(itemId);
        bid.setAmount(request.getAmount());
        bid.setBidder(request.getBidder());
        bid.setCreatedAt(Instant.now());
        return bidRepository.save(bid);
    }

    public void removeBid(String itemId, String bidId) {
        Bid bid = bidRepository.findById(bidId).orElseThrow(() -> new BidNotFoundException(bidId));
        if (!itemId.equals(bid.getItemId())) {
            throw new BidNotFoundException(bidId);
        }
        bidRepository.deleteById(bidId);
    }

    public void removeItemBids(String itemId) {
        bidRepository.deleteByItemId(itemId);
    }

    public List<Bid> getRemoteBids(String itemId) {
        return bidRepository.findByItemIdOrderByCreatedAtDesc(itemId);
    }
}
