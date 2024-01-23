package com.bid.auctionedge.service.impl.test;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bid.auctionedge.model.Bid;
import com.bid.auctionedge.repo.BidRepository;
import com.bid.auctionedge.service.ProductService;
import com.bid.auctionedge.service.impl.BidServiceImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BidServiceImplTest {

	@InjectMocks
	private BidServiceImpl bidService;

	@Mock
	private BidRepository bidRepository;

	@Mock
	private ProductService productService;

	public BidServiceImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void placeBid_shouldSaveBid() {
		Bid bid = new Bid();
		bid.setAmount(BigDecimal.valueOf(100.00));

		when(productService.getMinBidPriceForProduct(any())).thenReturn(BigDecimal.ZERO);
		when(bidRepository.save(any(Bid.class))).thenReturn(bid);

		bidService.placeBid(bid);

		verify(bidRepository, times(1)).save(any(Bid.class));
	}

	@Test
	void getWinningBid_shouldReturnWinningBid() {
		Long productId = 1L;
		Bid winningBid = new Bid();
		winningBid.setAmount(BigDecimal.valueOf(150.00));

		when(bidRepository.findByProductIdOrderByAmountDesc(productId))
				.thenReturn(Collections.singletonList(winningBid));

		Bid result = bidService.getWinningBid(productId);

		assertEquals(winningBid, result);
	}

	@Test
	void getBidsForProduct_shouldReturnListOfBids() {
		Long productId = 1L;
		List<Bid> bids = Collections.singletonList(new Bid());

		when(bidRepository.findByProduct(any())).thenReturn(bids);

		List<Bid> result = bidService.getBidsForProduct(productId);

		assertEquals(bids, result);
	}
}
