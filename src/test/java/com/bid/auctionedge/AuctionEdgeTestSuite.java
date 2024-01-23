package com.bid.auctionedge;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.jdbc.Sql;

import com.bid.auctionedge.controller.test.BidControllerTest;
import com.bid.auctionedge.controller.test.UserControllerTest;
import com.bid.auctionedge.service.impl.test.BidServiceImplTest;
import com.bid.auctionedge.service.impl.test.UserServiceImplTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BidWinnerApplicationTests.class, UserControllerTest.class, BidControllerTest.class,
		BidServiceImplTest.class, UserServiceImplTest.class })
public class AuctionEdgeTestSuite {

}
