package com.nedelingg.actions;

import com.nedelingg.cardvalue.Value;
import com.nedelingg.model.CompanyID;

public abstract class Actioner {
	
	private CompanyID company;
	private Value value;
	
	public Actioner(CompanyID company, Value value){
		this.setCompany(company);
		this.value = value;
	}

	public CompanyID getCompany() {
		return company;
	}

	public void setCompany(CompanyID company) {
		this.company = company;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
}
