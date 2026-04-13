package com.devops.bidder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "bidder")
@Validated
public class BidderProperties {

    private final Elasticsearch elasticsearch = new Elasticsearch();

    public Elasticsearch getElasticsearch() {
        return elasticsearch;
    }

    public static class Elasticsearch {

        @NotBlank
        private String indexName = "12345678";

        public String getIndexName() {
            return indexName;
        }

        public void setIndexName(String indexName) {
            this.indexName = indexName;
        }
    }
}
