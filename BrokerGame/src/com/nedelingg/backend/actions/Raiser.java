package com.nedelingg.backend.actions;

import com.nedelingg.backend.cardvalue.ValueRaiser;
import com.nedelingg.backend.companies.CompanyID;

public abstract class Raiser extends Actioner {

	public Raiser(CompanyID company, ValueRaiser value) {
		super(company, value);
	}
}
