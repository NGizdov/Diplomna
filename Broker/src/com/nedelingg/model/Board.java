package com.nedelingg.model;

import java.util.List;

public class Board {
	public static int MAX_SHARE_PRIZE = 250;
	public static int MIN_SHARE_PRIZE = 10;
	
	private List<Company> companies;

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
	
}
