package com.devops.bidder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BidderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BidderApplication.class, args);
    }
}
