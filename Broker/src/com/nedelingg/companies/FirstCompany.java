package com.nedelingg.companies;

import com.nedelingg.stock.FirstCompanyStock;

public class FirstCompany extends Company {

	public FirstCompany() {
		super(CompanyID.FIRST, new FirstCompanyStock());
	}
}
