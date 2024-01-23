package com.bid.auctionedge.service.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.auctionedge.controller.BidController;
import com.bid.auctionedge.enums.ErrorCodeEnum;
import com.bid.auctionedge.error.BidServiceException;
import com.bid.auctionedge.model.Bid;
import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.model.Product;
import com.bid.auctionedge.repo.BidRepository;
import com.bid.auctionedge.service.BidService;
import com.bid.auctionedge.service.ProductService;

@Service
public class BidServiceImpl implements BidService {

	private static final Logger logger = LoggerFactory.getLogger(BidServiceImpl.class);

	@Autowired
	private final BidRepository bidRepository;

	@Autowired
	private ProductService productService;

	public BidServiceImpl(BidRepository bidRepository, ProductService productService) {
		this.bidRepository = bidRepository;
		this.productService = productService;
	}

	@Override
	public Bid placeBid(Bid bid) {
		logger.info("Placing bid: {}", bid);
		// Validate the bid against the minimum price set by the seller
		validateBid(bid);
		// Save the bid to the database
		Bid savedBid = bidRepository.save(bid);
		logger.info("Bid placed successfully: {}", savedBid);
		return savedBid;
	}

	@Override
	public Bid getWinningBid(Long productId) {
		logger.info("Fetching winning bid for product ID: {}", productId);
		// Retrieve all bids for a specific product and find the bid with the highest
		// amount
		List<Bid> bids = bidRepository.findByProductIdOrderByAmountDesc(productId);

		if (!bids.isEmpty()) {
			Bid winningBid = bids.get(0);
			logger.info("Winning bid found: {}", winningBid);
			return winningBid; // The first buyer who bid the highest amount wins
		} else {
			logger.warn("No bids found for the product");
			throw new BidServiceException("No bids found for the product", ErrorCodeEnum.NO_BIDS_FOUND);
		}
	}

	@Override
	public List<Bid> getBidsForProduct(Long productId) {
		logger.info("Fetching bids for product ID: {}", productId);
		Product product = new Product();
		product.setId(productId);
		List<Bid> bids = bidRepository.findByProduct(product);
		logger.info("Fetched {} bids for product ID: {}", bids.size(), productId);
		return bids;
	}

	private void validateBid(Bid bid) {
		logger.info("Validating bid: {}", bid);
		// Retrieve the minimum price set by the seller for the product
		if (bid.getProduct() != null) {
			BigDecimal minimumPrice = productService.getMinBidPriceForProduct(bid.getProduct().getId());

			// Validate the bid against the minimum price
			if (bid.getAmount().compareTo(minimumPrice) < 0) {
				logger.warn("Bid amount is below the minimum price");
				throw new BidServiceException("Bid amount is below the minimum price",
						ErrorCodeEnum.INVALID_BID_AMOUNT);
			}
		}
	}

	@Override
	public Buyer getAuthenticatedBuyer() {
		Buyer defaultBuyer = new Buyer();
		defaultBuyer.setId(1L);
		logger.info("Returning authenticated buyer: {}", defaultBuyer);
		return defaultBuyer;
	}

}
