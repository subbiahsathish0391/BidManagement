package com.bid.auctionedge.service;

import java.util.Optional;

import com.bid.auctionedge.model.Buyer;

public interface BuyerService {
	Buyer registerBuyer(Buyer buyer);

	Optional<Buyer> getBuyerById(Long buyerId);
}
