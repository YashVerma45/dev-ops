package com.devops.bidder.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Logs effective index name at startup (successor to the static INDEX constant sketch).
 */
@Component
public class ElasticSearchManager {

    private static final Logger log = LoggerFactory.getLogger(ElasticSearchManager.class);

    private final BidderProperties bidderProperties;

    public ElasticSearchManager(BidderProperties bidderProperties) {
        this.bidderProperties = bidderProperties;
    }

    @PostConstruct
    void logIndex() {
        log.info("Elasticsearch indexName={}", bidderProperties.getElasticsearch().getIndexName());
    }

    public String getIndexName() {
        return bidderProperties.getElasticsearch().getIndexName();
    }
}
