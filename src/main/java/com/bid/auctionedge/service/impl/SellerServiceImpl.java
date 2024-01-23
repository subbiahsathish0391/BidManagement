package com.bid.auctionedge.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bid.auctionedge.model.Seller;
import com.bid.auctionedge.repo.SellerRepository;
import com.bid.auctionedge.service.SellerService;

public class SellerServiceImpl implements SellerService {

	private static final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

	private final SellerRepository sellerRepository;

	@Autowired
	public SellerServiceImpl(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}

	public Seller registerSeller(Seller seller) {
		logger.info("Creating seller: {}", seller);
		Seller createdSeller = sellerRepository.save(seller);
		logger.info("Seller created successfully: {}", createdSeller);
		return createdSeller;
	}

	public Optional<Seller> getSellerById(Long sellerId) {
		logger.info("Fetching seller by ID: {}", sellerId);
		return sellerRepository.findById(sellerId);
	}

}
