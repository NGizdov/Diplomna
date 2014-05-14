package com.nedelingg.stock;

import com.nedelingg.model.CompanyID;

public class Share {
	private int value;
	private CompanyID companyID;

	public Share(CompanyID companyID, int value){
		this.value = value;
		this.setCompanyID(companyID);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public CompanyID getCompanyID() {
		return companyID;
	}

	public void setCompanyID(CompanyID companyID) {
		this.companyID = companyID;
	}
}
