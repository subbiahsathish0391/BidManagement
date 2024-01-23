package com.bid.auctionedge.error;

import com.bid.auctionedge.enums.ErrorCodeEnum;

import lombok.Data;

@Data
public class ProductServiceException extends RuntimeException {

	private final ErrorCodeEnum errorCodeEnum;

	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}

	public ProductServiceException(ErrorCodeEnum errorCodeEnum, String message) {
		super(message);
		this.errorCodeEnum = errorCodeEnum;
	}

	public ProductServiceException(ErrorCodeEnum errorCodeEnum, String message, Object... params) {
		super(getFormattedMessage(message, params));
		this.errorCodeEnum = errorCodeEnum;
	}

	public ProductServiceException(Throwable e, ErrorCodeEnum errorCodeEnum, String message, Object... params) {
		super(getFormattedMessage(message, params), e);
		this.errorCodeEnum = errorCodeEnum;
	}

	static String getFormattedMessage(String message, Object... params) {
		return String.format(message, params);
	}

}
