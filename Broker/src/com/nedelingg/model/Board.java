package com.nedelingg.model;

import com.nedelingg.companies.Company;
import com.nedelingg.companies.CompanyID;
import com.nedelingg.companies.FirstCompany;
import com.nedelingg.companies.FourthCompany;
import com.nedelingg.companies.SecondCompany;
import com.nedelingg.companies.ThirdCompany;

public class Board {
	public static int MAX_SHARE_PRIZE = 250;
	public static int MIN_SHARE_PRIZE = 10;

	private FirstCompany firstCompany;
	private SecondCompany secondCompany;
	private ThirdCompany thirdCompany;
	private FourthCompany fourthCompany;
	
	public Board() {
		this.firstCompany = new FirstCompany();
		this.secondCompany = new SecondCompany();
		this.thirdCompany = new ThirdCompany();
		this.fourthCompany = new FourthCompany();
	}
	
	public void repaint(){
		// TODO
	}

	public void putShares(int intShares, CompanyID companyID) {
		if (companyID.equals(CompanyID.FIRST)) {
			this.firstCompany.putShare(intShares);
		} else if (companyID.equals(CompanyID.SECOND)) {
			this.secondCompany.putShare(intShares);
		} else if (companyID.equals(CompanyID.THIRD)) {
			this.thirdCompany.putShare(intShares);
		} else {
			this.fourthCompany.putShare(intShares);
		}
	}
	
	public boolean checkAvailableShares(int sharesAmount, CompanyID companyID){
		int available;
		if (companyID.equals(CompanyID.FIRST)) {
			available = this.firstCompany.availableShares();
		} else if (companyID.equals(CompanyID.SECOND)) {
			available = this.secondCompany.availableShares();
		} else if (companyID.equals(CompanyID.THIRD)) {
			available = this.thirdCompany.availableShares();
		} else {
			available = this.fourthCompany.availableShares();
		}
		return sharesAmount >= available;
	}

	public int getCompanyCurrentValue(CompanyID companyID) {
		int currentValue;
		if (companyID.equals(CompanyID.FIRST)) {
			currentValue = this.firstCompany.getCurrentValue();
		} else if (companyID.equals(CompanyID.SECOND)) {
			currentValue = this.secondCompany.getCurrentValue();
		} else if (companyID.equals(CompanyID.THIRD)) {
			currentValue = this.thirdCompany.getCurrentValue();
		} else {
			currentValue = this.fourthCompany.getCurrentValue();
		}
		return currentValue;
	}
}
