package com.bid.auctionedge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bid.auctionedge.model.Bid;
import com.bid.auctionedge.model.Product;

public interface BidRepository extends JpaRepository<Bid, Long> {
	List<Bid> findByProduct(Product product);

	List<Bid> findByProductIdOrderByAmountDesc(Long productId);

}
