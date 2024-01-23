package com.bid.auctionedge.error;

import com.bid.auctionedge.enums.ErrorCodeEnum;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class ErrorResponse {
	private final Integer errorCode;
	private final String errorMessage;
	private final ErrorCodeEnum errorType;

	public ErrorResponse(ProductServiceException exception) {
		this.errorType = exception.getErrorCodeEnum();
		this.errorCode = exception.getErrorCodeEnum().getCode();
		this.errorMessage = exception.getMessage();

	}
}
