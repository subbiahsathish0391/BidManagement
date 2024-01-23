package com.bid.auctionedge.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bid.auctionedge.model.Product;
import com.bid.auctionedge.model.Seller;
import com.bid.auctionedge.repo.ProductRepository;
import com.bid.auctionedge.repo.UserRepository;
import com.bid.auctionedge.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final ProductRepository productRepository;

	private final UserRepository userRepository;

	public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Product registerProduct(Product productInput) {
		Seller seller = userRepository.findById(productInput.getSeller().getId()).filter(user -> user instanceof Seller)
				.map(user -> (Seller) user).orElse(null);
		// Create a new Product
		Product product = new Product();
		product.setName(productInput.getName());
		product.setMinBidPrice(productInput.getMinBidPrice());
		product.setSeller(seller);
		Product registeredProduct = productRepository.save(product);
		logger.info("Product registered successfully: {}", registeredProduct);
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		logger.info("Fetching all products");
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long productId) {
		logger.info("Fetching product by ID: {}", productId);
		return productRepository.findById(productId).orElse(null);
	}

	@Override
	public BigDecimal getMinBidPriceForProduct(Long productId) {
		logger.info("Fetching minimum bid price for product ID: {}", productId);
		return productRepository.findMinBidPriceById(productId);
	}

}
