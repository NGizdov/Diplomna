package com.nedelingg.exceptions;

public class NotEnoughMoney extends Exception {

	private static final long serialVersionUID = -2776525957983954092L;
	
	public NotEnoughMoney() {
		super();
	}
	
	public NotEnoughMoney(String message){
		super(message);
	}
}
