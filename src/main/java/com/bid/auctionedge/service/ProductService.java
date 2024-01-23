package com.bid.auctionedge.service;

import java.math.BigDecimal;
import java.util.List;

import com.bid.auctionedge.model.Product;

public interface ProductService {
	Product registerProduct(Product product);

	List<Product> getAllProducts();

	Product getProductById(Long productId);

	BigDecimal getMinBidPriceForProduct(Long productId);
}
