package com.nedelingg.actions;

import com.nedelingg.model.Company;
import com.nedelingg.value.Value;

public abstract class Actioner {
	
	private Company company;
	private Value value;
	
	public Actioner(Company company, Value value){
		this.company = company;
		this.value = value;
	}
}
