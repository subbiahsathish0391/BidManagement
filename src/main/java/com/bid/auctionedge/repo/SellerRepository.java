package com.bid.auctionedge.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bid.auctionedge.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {


}
