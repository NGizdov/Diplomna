package com.nedelingg.backend.actions;

import com.nedelingg.backend.cardvalue.ValueLowerer;
import com.nedelingg.backend.companies.CompanyID;

public abstract class Lowerer extends Actioner {

	public Lowerer(CompanyID company, ValueLowerer value) {
		super(company, value);
	}

}
