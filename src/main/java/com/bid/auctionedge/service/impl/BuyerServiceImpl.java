package com.bid.auctionedge.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.repo.BuyerRepository;
import com.bid.auctionedge.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService {

	private static final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);

	private final BuyerRepository buyerRepository;

	@Autowired
	public BuyerServiceImpl(BuyerRepository buyerRepository) {
		this.buyerRepository = buyerRepository;
	}

	public Buyer registerBuyer(Buyer buyer) {
		logger.info("Registering buyer: {}", buyer);
		Buyer registeredBuyer = buyerRepository.save(buyer);
		logger.info("Buyer registered successfully: {}", registeredBuyer);
		return registeredBuyer;
	}

	public Optional<Buyer> getBuyerById(Long buyerId) {
		logger.info("Fetching buyer by ID: {}", buyerId);
		return buyerRepository.findById(buyerId);
	}

}
