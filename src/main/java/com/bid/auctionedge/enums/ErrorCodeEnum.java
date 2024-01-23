package com.bid.auctionedge.enums;

public enum ErrorCodeEnum {
	PRODUCT_NOT_FOUND(4001, "Product not found"),
	USER_NOT_FOUND(4002, "User not found"),
	INVALID_BID_AMOUNT(4003, "Invalid Bid Amount"),
	NO_BIDS_FOUND(4004, "No Bids Found");
	private final int code;
	private final String description;

	private ErrorCodeEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return code + "" + description;
	}

	public int getExceptionLevel() {
		if (this.code < 4000) {
			return 1;
		} else if (this.code < 5000) {
			return 2;
		} else {
			return 3;
		}
	}
}
