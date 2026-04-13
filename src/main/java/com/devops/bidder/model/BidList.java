package com.devops.bidder.model;

import com.devops.bidder.domain.Bid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * View-layer collection for bids (replaces the original string-based prototype).
 */
public class BidList {

    private final List<Bid> bids;

    public BidList(List<Bid> bids) {
        this.bids = bids == null ? new ArrayList<Bid>() : new ArrayList<Bid>(bids);
    }

    public List<Bid> getBids() {
        return Collections.unmodifiableList(bids);
    }

    public int size() {
        return bids.size();
    }
}
