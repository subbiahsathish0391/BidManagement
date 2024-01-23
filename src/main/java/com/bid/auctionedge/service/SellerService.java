package com.bid.auctionedge.service;

import java.util.Optional;

import com.bid.auctionedge.model.Seller;

public interface SellerService {
	Seller registerSeller(Seller seller);

	Optional<Seller> getSellerById(Long sellerId);
}
