package com.nedelingg.backend.exceptions;

public class NotEnoughShares extends Exception {

	private static final long serialVersionUID = -1783569360528131906L;
	
	public NotEnoughShares() {
		super();
	}
	
	public NotEnoughShares(String message) {
		super(message);
	}
}
