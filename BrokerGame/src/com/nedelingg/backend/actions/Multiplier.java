package com.nedelingg.backend.actions;

import com.nedelingg.backend.cardvalue.ValueMultiplier;
import com.nedelingg.backend.companies.CompanyID;

public class Multiplier extends Raiser {

	public Multiplier(CompanyID company, ValueMultiplier  value) {
		super(company, value);
	}

}
