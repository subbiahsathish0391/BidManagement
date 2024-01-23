package com.bid.auctionedge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Inheritance
@DiscriminatorColumn(name = "user_type")
public class AuctionUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Hidden
	private Long id;

	private String username;
	private String email;
	@Hidden
	private String token;
}