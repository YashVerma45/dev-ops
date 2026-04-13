package com.devops.bidder.repository;

import com.devops.bidder.domain.Bid;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BidRepository extends ElasticsearchRepository<Bid, String> {

    List<Bid> findByItemIdOrderByCreatedAtDesc(String itemId);

    void deleteByItemId(String itemId);
}
