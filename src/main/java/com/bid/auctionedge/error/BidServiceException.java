package com.bid.auctionedge.error;

import com.bid.auctionedge.enums.ErrorCodeEnum;

public class BidServiceException extends RuntimeException {

	private final ErrorCodeEnum errorCodeEnum;

	public BidServiceException(String message, ErrorCodeEnum errorCodeEnum) {
		super(message);
		this.errorCodeEnum = errorCodeEnum;
	}

	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}
}
