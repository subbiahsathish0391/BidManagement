package com.bid.auctionedge.error;

import com.bid.auctionedge.enums.ErrorCodeEnum;

public class UserServiceException extends RuntimeException {

	private final ErrorCodeEnum errorCodeEnum;

	public UserServiceException(String message, ErrorCodeEnum errorCodeEnum) {
		super(message);
		this.errorCodeEnum = errorCodeEnum;
	}

	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}
}
