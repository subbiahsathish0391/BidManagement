package com.bid.auctionedge.controller.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bid.auctionedge.controller.UserController;
import com.bid.auctionedge.service.UserService;
import com.bid.auctionedge.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

import com.bid.auctionedge.model.AuctionUser;
import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.model.Seller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserServiceImpl userService;

	private MockMvc mockMvc;

	public UserControllerTest() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	void getUserById_shouldReturnUser() throws Exception {
		Long userId = 2L;
		AuctionUser user = new Buyer();
		user.setId(userId);
		when(userService.getUserById(userId)).thenReturn(user);
		mockMvc.perform(get("/users/{userId}", userId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(userId));

		verify(userService, times(1)).getUserById(userId);
	}

	@Test
	void getAllBuyers_shouldReturnListOfBuyers() throws Exception {
		List<Buyer> buyers = Arrays.asList(new Buyer(), new Buyer());

		when(userService.getAllBuyers()).thenReturn(buyers);

		mockMvc.perform(get("/users/listBuyers")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));

		verify(userService, times(1)).getAllBuyers();
	}

	@Test
	void getAllSellers_shouldReturnListOfSellers() throws Exception {
		List<Seller> sellers = Arrays.asList(new Seller(), new Seller());

		when(userService.getAllSellers()).thenReturn(sellers);

		mockMvc.perform(get("/users/listSellers")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));

		verify(userService, times(1)).getAllSellers();
	}

	@Test
	void createBuyer_shouldReturnCreated() throws Exception {
		Buyer buyer = new Buyer();
		buyer.setId(2L);
		when(userService.createBuyer(any(Buyer.class))).thenReturn(buyer);

		mockMvc.perform(post("/users/createBuyer").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists());

		verify(userService, times(1)).createBuyer(any(Buyer.class));
	}

	@Test
	void createSeller_shouldReturnCreated() throws Exception {
		Seller seller = new Seller();
		seller.setId(1L);
		when(userService.createSeller(any(Seller.class))).thenReturn(seller);

		mockMvc.perform(post("/users/createSeller").contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").exists());

		verify(userService, times(1)).createSeller(any(Seller.class));
	}
}
