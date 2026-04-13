package com.devops.bidder.service;

public class BidNotFoundException extends RuntimeException {

    public BidNotFoundException(String bidId) {
        super("Bid not found: " + bidId);
    }
}
