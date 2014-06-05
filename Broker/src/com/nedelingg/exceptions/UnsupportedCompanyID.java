package com.nedelingg.exceptions;

public class UnsupportedCompanyID  extends Exception {
	
	private static final long serialVersionUID = 8747039704714928590L;

	public UnsupportedCompanyID() {
		super();
	}
	
	public UnsupportedCompanyID(String message) {
		super(message);
	}
}
