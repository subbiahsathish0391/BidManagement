package com.bid.auctionedge.service;

import java.util.List;

import com.bid.auctionedge.model.Bid;
import com.bid.auctionedge.model.Buyer;

public interface BidService {
	Bid placeBid(Bid bid);

	Bid getWinningBid(Long productId);
	
	List<Bid> getBidsForProduct(Long productId);
	
	Buyer getAuthenticatedBuyer();
}
