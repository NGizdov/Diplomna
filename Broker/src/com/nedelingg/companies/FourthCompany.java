package com.nedelingg.companies;

import com.nedelingg.stock.FourthCompanyStock;

public class FourthCompany extends Company {

	public FourthCompany() {
		super(CompanyID.FIRST, new FourthCompanyStock());
	}
}
