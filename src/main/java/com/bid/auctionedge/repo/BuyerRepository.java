package com.bid.auctionedge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bid.auctionedge.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {

}
