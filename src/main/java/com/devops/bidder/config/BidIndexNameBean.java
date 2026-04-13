package com.devops.bidder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BidIndexNameBean {

    @Bean("bidIndexName")
    String bidIndexName(BidderProperties properties) {
        return properties.getElasticsearch().getIndexName();
    }
}
