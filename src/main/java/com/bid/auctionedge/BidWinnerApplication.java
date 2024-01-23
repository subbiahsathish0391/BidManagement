package com.bid.auctionedge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class BidWinnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidWinnerApplication.class, args);
	}

}
