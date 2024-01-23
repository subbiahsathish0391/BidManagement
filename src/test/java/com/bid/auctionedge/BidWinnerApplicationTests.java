package com.bid.auctionedge;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.bid.auctionedge.model.Bid;
import com.bid.auctionedge.model.Buyer;
import com.bid.auctionedge.model.Product;
import com.bid.auctionedge.model.Seller;
import com.bid.auctionedge.repo.BidRepository;
import com.bid.auctionedge.repo.ProductRepository;
import com.bid.auctionedge.repo.SellerRepository;
import com.bid.auctionedge.repo.UserRepository;
import com.bid.auctionedge.service.BidService;
import com.bid.auctionedge.service.BuyerService;
import com.bid.auctionedge.service.ProductService;
import com.bid.auctionedge.service.SellerService;
import com.bid.auctionedge.service.impl.BidServiceImpl;
import com.bid.auctionedge.service.impl.BuyerServiceImpl;
import com.bid.auctionedge.service.impl.ProductServiceImpl;
import com.bid.auctionedge.service.impl.SellerServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BidWinnerApplicationTests {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
    private UserRepository userRepository;
	
	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private BidServiceImpl bidService;

	@Mock
	private BidRepository bidRepository;

	@Mock
	private SellerRepository sellerRepository;

	@Mock
	private BuyerServiceImpl buyerService;

	@Mock
	private SellerServiceImpl sellerService;

	public BidWinnerApplicationTests() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void sellerCanRegisterNewProduct() {
		Seller seller = new Seller();
		seller.setUsername("seller123");
		seller.setEmail("seller@example.com");
		seller.setCompanyName("ABC Auctions");

		Product product = new Product();
		product.setName("Smartphone XYZ");
		product.setMinBidPrice(BigDecimal.valueOf(500.00));
		product.setSeller(seller);

		when(sellerRepository.save(any(Seller.class))).thenReturn(seller);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product registeredProduct = productService.registerProduct(product);

		assertNotNull(registeredProduct);
		assertEquals(product.getName(), registeredProduct.getName());
		assertEquals(product.getMinBidPrice(), registeredProduct.getMinBidPrice());
		assertEquals(product.getSeller(), registeredProduct.getSeller());
	}

	@Test
	void buyerCanPlaceBidInAuction() {
		Buyer buyer = new Buyer();
		buyer.setUsername("buyer456");
		buyer.setEmail("buyer@example.com");
		buyer.setPreferredPaymentMethod("Credit Card");

		Product product = new Product();
		product.setId(1L);
		product.setName("Smartphone XYZ");
		product.setMinBidPrice(BigDecimal.valueOf(500.00));

		Bid bidRequest = new Bid();
		bidRequest.setProduct(product);
		bidRequest.setAmount(BigDecimal.valueOf(550.00));
		bidRequest.setBuyer(buyer);

		when(bidService.getAuthenticatedBuyer()).thenReturn(buyer);
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		when(bidRepository.save(any(Bid.class))).thenAnswer(invocation -> {
			Bid savedBid = invocation.getArgument(0);
			savedBid.setId(1L); // Simulate saving to the database with an assigned ID
			return savedBid;
		});

		assertNotNull(bidRequest);
		assertEquals(buyer, bidRequest.getBuyer());
		assertEquals(product, bidRequest.getProduct());
		assertEquals(bidRequest.getAmount(), bidRequest.getAmount());
	}

	@Test
	void sellerCanEndAuctionAndSeeWinnerAndBid() {
	    Seller seller = new Seller();
	    seller.setUsername("seller123");
	    seller.setEmail("seller@example.com");
	    seller.setCompanyName("ABC Auctions");

	    Product product = new Product();
	    product.setId(1L);
	    product.setName("Smartphone XYZ");
	    product.setMinBidPrice(BigDecimal.valueOf(500.00));
	    product.setSeller(seller);

	    Bid winningBid = new Bid();
	    winningBid.setId(1L);
	    winningBid.setBuyer(new Buyer());
	    winningBid.setProduct(product);
	    winningBid.setAmount(BigDecimal.valueOf(550.00));

	    List<Bid> bids = new ArrayList<>();
	    bids.add(winningBid);

	    when(productRepository.findById(1L)).thenReturn(Optional.of(product));
	    when(bidRepository.findByProductIdOrderByAmountDesc(product.getId())).thenReturn(bids);

	    List<Bid> bidss = bidRepository.findByProductIdOrderByAmountDesc(1L);

	    Bid auctionResult = bidService.getWinningBid(1L);

	    assertNotNull(auctionResult);
	    assertEquals(product, auctionResult.getProduct());
	    assertNotNull(auctionResult.getBuyer());
	    assertEquals(winningBid.getBuyer(), auctionResult.getBuyer());
	    assertNotNull(auctionResult.getAmount());
	    assertEquals(winningBid.getAmount(), auctionResult.getAmount());
	}

}
