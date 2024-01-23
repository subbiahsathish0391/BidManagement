package com.bid.auctionedge.error;

import org.springframework.stereotype.Component;

import com.bid.auctionedge.enums.ErrorCodeEnum;

import lombok.Data;

@Data
@Component
public class ErrorResponseComposer {
	private ErrorCodeEnum errorCode;
	private String errorMessage;
}
