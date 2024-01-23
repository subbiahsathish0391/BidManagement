package com.bid.auctionedge.service.impl.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bid.auctionedge.repo.UserRepository;
import com.bid.auctionedge.service.impl.UserServiceImpl;

import com.bid.auctionedge.model.AuctionUser;
import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.model.Seller;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	public UserServiceImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createUser_shouldSaveUser() {
		AuctionUser user = new Buyer(); 

		when(userRepository.save(any(AuctionUser.class))).thenReturn(user);

		userService.createUser(user);

		verify(userRepository, times(1)).save(any(AuctionUser.class));
	}

	@Test
	void getUserById_shouldReturnUser() {
		Long userId = 1L;
		AuctionUser user = new Buyer(); 

		when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));

		AuctionUser result = userService.getUserById(userId);

		assertEquals(user, result);
	}

	@Test
	void getAllBuyers_shouldReturnListOfBuyers() {
		List<Buyer> buyers = Arrays.asList(new Buyer(), new Buyer());

		when(userRepository.findAllBuyers()).thenReturn(buyers);

		List<Buyer> result = userService.getAllBuyers();

		assertEquals(buyers, result);
	}

	@Test
	void getAllSellers_shouldReturnListOfSellers() {
		List<Seller> sellers = Arrays.asList(new Seller(), new Seller());

		when(userRepository.findAllSellers()).thenReturn(sellers);

		List<Seller> result = userService.getAllSellers();

		assertEquals(sellers, result);
	}

	@Test
	void createBuyer_shouldSaveBuyer() {
		Buyer buyer = new Buyer();

		when(userRepository.save(any(Buyer.class))).thenReturn(buyer);

		Buyer result = userService.createBuyer(buyer);

		assertEquals(buyer, result);
		verify(userRepository, times(1)).save(any(Buyer.class));
	}

	@Test
	void createSeller_shouldSaveSeller() {
		Seller seller = new Seller();

		when(userRepository.save(any(Seller.class))).thenReturn(seller);

		Seller result = userService.createSeller(seller);

		assertEquals(seller, result);
		verify(userRepository, times(1)).save(any(Seller.class));
	}
}
