package com.bid.auctionedge.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bid.auctionedge.error.ProductServiceException;
import com.bid.auctionedge.model.Bid;
import com.bid.auctionedge.model.Product;
import com.bid.auctionedge.service.BidService;
import com.bid.auctionedge.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.bid.auctionedge.enums.ErrorCodeEnum;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "Operations related to product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	private final ProductService productService;
	private final BidService bidService;

	public ProductController(ProductService productService, BidService bidService) {
		this.productService = productService;
		this.bidService = bidService;
	}

	@PostMapping("/register")
	public ResponseEntity<Product> registerProduct(@RequestBody Product product) {
		logger.info("Registering product: {}", product);
		Product registeredProduct = productService.registerProduct(product);
		logger.info("Product registered successfully: {}", registeredProduct);
		return new ResponseEntity<>(registeredProduct, HttpStatus.CREATED);
	}

	@PostMapping("/end")
	public ResponseEntity<String> endAuction(@RequestParam Long productId) {
		logger.info("Ending auction for product with ID: {}", productId);

		Product product = productService.getProductById(productId);
		if (product == null) {
			logger.warn("Product not found for ID: {}", productId);
			throw new ProductServiceException(ErrorCodeEnum.PRODUCT_NOT_FOUND, "Product Not Found");
		}

		Bid winningBid = bidService.getWinningBid(productId);
		if (winningBid == null) {
			logger.info("No winning bid found for product with ID: {}", productId);
			return new ResponseEntity<>("No Winning Bid Found", HttpStatus.NOT_FOUND);
		}

		String resultMessage = "Auction ended. Winner: " + winningBid.getBuyer().getUsername() + ", Winning Bid: "
				+ winningBid.getAmount();
		logger.info(resultMessage);
		return new ResponseEntity<>(resultMessage, HttpStatus.OK);
	}

	@GetMapping("/getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		logger.info("Fetching all products");
		List<Product> products = productService.getAllProducts();
		logger.info("Fetched {} products", products.size());
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

}
