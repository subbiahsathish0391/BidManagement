package com.bid.auctionedge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bid.auctionedge.model.Bid;
import com.bid.auctionedge.service.BidService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/bids")
@Tag(name = "Bid", description = "Operations related to bids")
public class BidController {

	private static final Logger logger = LoggerFactory.getLogger(BidController.class);
	
	@Autowired
	private BidService bidService;

	@PostMapping("/placeBid")
	public Bid placeBid(@RequestBody Bid bid) {
		logger.info("Placing bid: {}", bid);
		Bid placedBid = bidService.placeBid(bid);
		logger.info("Bid placed successfully: {}", placedBid);
		return placedBid;

	}

	@GetMapping("/product/{productId}")
	public List<Bid> getBidsForProduct(@PathVariable Long productId) {
		logger.info("Fetching bids for product with ID: {}", productId);
		List<Bid> bids = bidService.getBidsForProduct(productId);
        logger.info("Bids for product with ID {}: {}", productId, bids);
        return bids;
	}

	@GetMapping("/winner/{productId}")
	public Bid getWinningBid(@PathVariable Long productId) {
        logger.info("Fetching winning bid for product with ID: {}", productId);
        Bid winningBid = bidService.getWinningBid(productId);
        logger.info("Winning bid for product with ID {}: {}", productId, winningBid);
        return winningBid;
    }
}
