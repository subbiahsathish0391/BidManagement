package com.bid.auctionedge.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("BUYER")
public class Buyer extends AuctionUser {

	private String preferredPaymentMethod;

	@OneToMany(mappedBy = "buyer")
	@JsonManagedReference
	private List<Bid> placedBids;

}
