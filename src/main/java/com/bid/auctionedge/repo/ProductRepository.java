package com.bid.auctionedge.repo;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bid.auctionedge.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT p.minBidPrice FROM Product p WHERE p.id = :productId")
	BigDecimal findMinBidPriceById(@Param("productId") Long productId);
}
