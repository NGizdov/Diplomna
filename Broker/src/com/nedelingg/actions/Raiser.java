package com.nedelingg.actions;

import com.nedelingg.cardvalue.ValueRaiser;
import com.nedelingg.model.CompanyID;

public abstract class Raiser extends Actioner {

	public Raiser(CompanyID company, ValueRaiser value) {
		super(company, value);
	}
}
