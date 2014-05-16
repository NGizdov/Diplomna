package com.nedelingg.actions;

import com.nedelingg.cardvalue.ValueLowerer;
import com.nedelingg.companies.CompanyID;

public abstract class Lowerer extends Actioner {

	public Lowerer(CompanyID company, ValueLowerer value) {
		super(company, value);
	}

}
