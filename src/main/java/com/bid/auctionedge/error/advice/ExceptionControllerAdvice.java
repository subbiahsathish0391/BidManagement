package com.bid.auctionedge.error.advice;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bid.auctionedge.enums.ErrorCodeEnum;
import com.bid.auctionedge.error.ErrorResponse;
import com.bid.auctionedge.error.ProductServiceException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	public static Integer validateAndFetchErrorKey(Exception e) {
		String message = ExceptionUtils.getRootCause(e).getMessage();
		if (message.contains("Key")) {
			return message.indexOf("key");
		} else if (message.contains("ERROR")) {
			return message.indexOf("ERROR");
		} else {
			return 0;
		}
	}

	@ExceptionHandler(ProductServiceException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ProductServiceException exception) {
		ErrorCodeEnum errorCode = exception.getErrorCodeEnum();
		BodyBuilder res;
		switch (errorCode.getExceptionLevel()) {
		case 1:
		case 2:
			res = ResponseEntity.badRequest();
			break;
		case 3:
			res = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		default:
			res = ResponseEntity.badRequest();
		}
		return res.body(new ErrorResponse(exception));

	}

}
