package com.nedelingg.actions;

import com.nedelingg.cardvalue.Value;
import com.nedelingg.companies.CompanyID;

public abstract class Actioner {
	
	private CompanyID company;
	private Value value;
	
	public Actioner(CompanyID company, Value value){
		this.company = company;
		this.value = value;
	}

	public CompanyID getCompany() {
		return company;
	}

	public Value getValue() {
		return value;
	}
}
