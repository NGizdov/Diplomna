package com.nedelingg.backend.actions;

import com.nedelingg.backend.cardvalue.Value;
import com.nedelingg.backend.companies.CompanyID;

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
