package com.nedelingg.companies;

import com.nedelingg.stock.ThirdCompanyStock;

public class ThirdCompany extends Company {

	public ThirdCompany() {
		super(CompanyID.FIRST, new ThirdCompanyStock());
	}
}