package com.bid.auctionedge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bid.auctionedge.model.AuctionUser;
import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.model.Seller;
import com.bid.auctionedge.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Operations related to users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
		logger.info("Fetching user by ID: {}", userId);
		AuctionUser user = userService.getUserById(userId);
		logger.info("User fetched successfully: {}", user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/listBuyers")
	public ResponseEntity<List<Buyer>> getAllBuyers() {
		logger.info("Fetching all buyers");
		List<Buyer> buyers = userService.getAllBuyers();
		logger.info("Fetched {} buyers", buyers.size());
		return new ResponseEntity<>(buyers, HttpStatus.OK);
	}

	@GetMapping("/listSellers")
	public ResponseEntity<List<Seller>> getAllSellers() {
		logger.info("Fetching all sellers");
		List<Seller> sellers = userService.getAllSellers();
		logger.info("Fetched {} sellers", sellers.size());
		return new ResponseEntity<>(sellers, HttpStatus.OK);
	}

	@PostMapping("/createBuyer")
	public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
		logger.info("Creating buyer: {}", buyer);
		Buyer createdBuyer = userService.createBuyer(buyer);
		logger.info("Buyer created successfully: {}", createdBuyer);
		return new ResponseEntity<>(createdBuyer, HttpStatus.CREATED);
	}

	@PostMapping("/createSeller")
	public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
		logger.info("Creating seller: {}", seller);
		Seller createdSeller = userService.createSeller(seller);
		logger.info("Seller created successfully: {}", createdSeller);
		return new ResponseEntity<>(createdSeller, HttpStatus.CREATED);
	}

}
