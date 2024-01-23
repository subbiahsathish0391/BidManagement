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
@DiscriminatorValue("SELLER")
public class Seller extends AuctionUser {

	private String companyName;

	@OneToMany(mappedBy = "seller")
	@JsonManagedReference
	private List<Product> listedProducts;

}
