package com.devops.bidder.web;

import com.devops.bidder.command.AddBidCommand;
import com.devops.bidder.command.DeleteBidCommand;
import com.devops.bidder.domain.Bid;
import com.devops.bidder.service.BidService;
import com.devops.bidder.web.dto.AddBidRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class BidRestController {

    private final AddBidCommand addBidCommand;
    private final DeleteBidCommand deleteBidCommand;
    private final BidService bidService;

    public BidRestController(AddBidCommand addBidCommand, DeleteBidCommand deleteBidCommand, BidService bidService) {
        this.addBidCommand = addBidCommand;
        this.deleteBidCommand = deleteBidCommand;
        this.bidService = bidService;
    }

    @PostMapping("/{itemId}/bids")
    public ResponseEntity<Bid> addBid(@PathVariable String itemId, @Valid @RequestBody AddBidRequest request) {
        Bid created = addBidCommand.execute(itemId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{itemId}/bids/{bidId}")
    public ResponseEntity<Void> removeBid(@PathVariable String itemId, @PathVariable String bidId) {
        deleteBidCommand.execute(itemId, bidId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{itemId}/bids")
    public ResponseEntity<Void> removeItemBids(@PathVariable String itemId) {
        bidService.removeItemBids(itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{itemId}/bids")
    public List<Bid> getBids(@PathVariable String itemId) {
        return bidService.getRemoteBids(itemId);
    }
}
