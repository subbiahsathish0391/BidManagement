package com.bid.auctionedge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bid.auctionedge.model.AuctionUser;
import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.model.Seller;

public interface UserRepository extends JpaRepository<AuctionUser, Long> {

	@Query("SELECT b FROM Buyer b")
	List<Buyer> findAllBuyers();

	@Query("SELECT s FROM Seller s")
	List<Seller> findAllSellers();

}
