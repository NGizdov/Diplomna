package com.nedelingg.backend.actions;

import com.nedelingg.backend.cardvalue.ValueSubstractor;
import com.nedelingg.backend.companies.CompanyID;

public class Substractor extends Lowerer {

	public Substractor(CompanyID company, ValueSubstractor value) {
		super(company, value);
	}
}
