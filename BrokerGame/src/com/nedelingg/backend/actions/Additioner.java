package com.nedelingg.backend.actions;

import com.nedelingg.backend.cardvalue.ValueAdditioner;
import com.nedelingg.backend.companies.CompanyID;

public class Additioner extends Raiser {

	public Additioner(CompanyID company, ValueAdditioner value) {
		super(company, value);
	}

}
