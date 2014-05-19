package com.nedelingg.companies;

import com.nedelingg.stock.SecondCompanyStock;

public class SecondCompany extends Company {

	public SecondCompany() {
		super(CompanyID.FIRST, new SecondCompanyStock());
	}
}
