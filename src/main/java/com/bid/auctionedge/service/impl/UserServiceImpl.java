package com.bid.auctionedge.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bid.auctionedge.enums.ErrorCodeEnum;
import com.bid.auctionedge.error.ProductServiceException;
import com.bid.auctionedge.model.AuctionUser;
import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.model.Seller;
import com.bid.auctionedge.repo.UserRepository;
import com.bid.auctionedge.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public AuctionUser createUser(AuctionUser user) {
		logger.info("Creating user: {}", user);
		// Generate a unique token for the user
		String token = generateToken();
		// Hash the token before storing it
		String hashedToken = passwordEncoder.encode(token);
		user.setToken(hashedToken);
		AuctionUser savedUser = userRepository.save(user);
		logger.info("User created successfully: {}", savedUser);
		return savedUser;
	}

	@Override
	public AuctionUser getUserById(Long userId) {
		logger.info("Fetching user by ID: {}", userId);
		return userRepository.findById(userId)
				.orElseThrow(() -> new ProductServiceException(ErrorCodeEnum.USER_NOT_FOUND, "User not found"));
	}

	private String generateToken() {
		// Generate a unique token using UUID
		return UUID.randomUUID().toString();
	}

	@Override
	public List<Buyer> getAllBuyers() {
		logger.info("Fetching all buyers");
		return userRepository.findAllBuyers();
	}

	@Override
	public List<Seller> getAllSellers() {
		logger.info("Fetching all sellers");
		return userRepository.findAllSellers();
	}

	@Override
	public Buyer createBuyer(Buyer buyer) {
		logger.info("Creating buyer: {}", buyer);
		return userRepository.save(buyer);
	}

	@Override
	public Seller createSeller(Seller seller) {
		logger.info("Creating seller: {}", seller);
		return userRepository.save(seller);
	}

}
