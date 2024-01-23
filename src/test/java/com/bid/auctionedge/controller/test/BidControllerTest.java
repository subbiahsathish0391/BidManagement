package com.bid.auctionedge.controller.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bid.auctionedge.controller.BidController;
import com.bid.auctionedge.model.Bid;
import com.bid.auctionedge.service.impl.BidServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BidControllerTest {

	@InjectMocks
	private BidController bidController;

	@Mock
	private BidServiceImpl bidService;

	private MockMvc mockMvc;

	public BidControllerTest() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();
	}

	@Test
	void placeBid_shouldReturnCreated() throws Exception {
		Bid bid = new Bid();
		bid.setAmount(BigDecimal.valueOf(100.00));

		when(bidService.placeBid(any(Bid.class))).thenReturn(bid);

		mockMvc.perform(
				post("/bids/placeBid").contentType(MediaType.APPLICATION_JSON).content("{ \"amount\": 100.00 }"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.amount").value(100.00));

		verify(bidService, times(1)).placeBid(any(Bid.class));
	}

	@Test
	void getBidsForProduct_shouldReturnListOfBids() throws Exception {
		Long productId = 1L;
		List<Bid> bids = Arrays.asList(new Bid(), new Bid());

		when(bidService.getBidsForProduct(productId)).thenReturn(bids);

		mockMvc.perform(get("/bids/product/{productId}", productId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));

		verify(bidService, times(1)).getBidsForProduct(productId);
	}

	@Test
	void getWinningBid_shouldReturnWinningBid() throws Exception {
		Long productId = 2L;
		Bid winningBid = new Bid();
		winningBid.setAmount(BigDecimal.valueOf(150.00));

		when(bidService.getWinningBid(productId)).thenReturn(winningBid);

		mockMvc.perform(get("/bids/winner/{productId}", productId)).andExpect(status().isOk())
				.andExpect(jsonPath("$.amount").value(150.00));

		verify(bidService, times(1)).getWinningBid(productId);
	}
}
