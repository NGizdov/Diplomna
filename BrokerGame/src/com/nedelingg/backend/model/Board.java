package com.nedelingg.backend.model;

import com.nedelingg.backend.actions.Actioner;
import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.companies.FirstCompany;
import com.nedelingg.backend.companies.FourthCompany;
import com.nedelingg.backend.companies.SecondCompany;
import com.nedelingg.backend.companies.ThirdCompany;
import com.nedelingg.backend.exceptions.NotEnoughShares;
import com.nedelingg.backend.exceptions.UnsupportedCompanyID;
import com.nedelingg.design.OptionActivity;

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

	public int putShares(int shares, CompanyID companyID) throws UnsupportedCompanyID {
		int moneys = shares * this.getCompanyCurrentValue(companyID);
		switch(companyID.value()){
			case 1: 
				this.firstCompany.putShare(shares);
				return moneys;
			case 2: 
				this.secondCompany.putShare(shares);
				return moneys;
			case 3: 
				this.thirdCompany.putShare(shares);
				return moneys;
			case 4: 
				this.fourthCompany.putShare(shares);
				return moneys;
			default: 
				throw new UnsupportedCompanyID("Not supported company ID");
		}
	}
	
	public int getShares(int intShares, CompanyID companyID) throws NotEnoughShares, UnsupportedCompanyID {
		switch(companyID.value()){
			case 1: 
				return firstCompany.getShare(intShares);
			case 2: 
				return secondCompany.getShare(intShares);
			case 3: 
				return thirdCompany.getShare(intShares);
			case 4: 
				return fourthCompany.getShare(intShares);
			default: 
				throw new UnsupportedCompanyID("Not supported company ID");
		}	
		
	}
	
	public boolean checkAvailableShares(int sharesAmount, CompanyID companyID) throws UnsupportedCompanyID{
		return sharesAmount <= this.getAvailableSharesCount(companyID);
	}

	public int getCompanyCurrentValue(CompanyID companyID) throws UnsupportedCompanyID {
		switch(companyID.value()){
			case 1: 
				return this.firstCompany.getCurrentValue();
			case 2: 
				return this.secondCompany.getCurrentValue();
			case 3: 
				return this.thirdCompany.getCurrentValue();
			case 4: 
				return this.fourthCompany.getCurrentValue();
			default: 
				throw new UnsupportedCompanyID("Not supported company ID");
		}
	}
	
	public void changeCompanyValue(CompanyID companyID, Actioner action) throws UnsupportedCompanyID{
		switch(companyID.value()){
			case 1: 
				this.firstCompany.changeCurrentValue(action);
				break;
			case 2: this.secondCompany.changeCurrentValue(action);
				break;
			case 3: 
				this.thirdCompany.changeCurrentValue(action);
				break;
			case 4: 
				this.fourthCompany.changeCurrentValue(action);
				break;
			default: throw new UnsupportedCompanyID("Not supported company ID");
		}
	}
	
	public void changeAllCompaniesValue(Actioner action) {
		this.firstCompany.changeCurrentValue(action);
		this.secondCompany.changeCurrentValue(action);
		this.thirdCompany.changeCurrentValue(action);
		this.fourthCompany.changeCurrentValue(action);
	}
	
	public int getAvailableSharesCount(CompanyID companyID) throws UnsupportedCompanyID{
		switch(companyID.value()){
			case 1: 
				return this.firstCompany.availableShares();
			case 2: 
				return this.secondCompany.availableShares();
			case 3: 
				return this.thirdCompany.availableShares();
			case 4: 
				return this.fourthCompany.availableShares();
			default: 
				throw new UnsupportedCompanyID("Not supported company ID");
		}	
	}
}
