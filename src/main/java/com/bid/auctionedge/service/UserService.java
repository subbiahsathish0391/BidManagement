package com.bid.auctionedge.service;

import java.util.List;

import com.bid.auctionedge.model.AuctionUser;
import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.model.Seller;

public interface UserService {
	AuctionUser createUser(AuctionUser user);

	AuctionUser getUserById(Long userId);

	List<Buyer> getAllBuyers();

	List<Seller> getAllSellers();

	Buyer createBuyer(Buyer buyer);

	Seller createSeller(Seller seller);
}
