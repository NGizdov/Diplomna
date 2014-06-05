package com.nedelingg.model;

import java.util.List;

import com.nedelingg.actions.Actioner;
import com.nedelingg.companies.CompanyID;
import com.nedelingg.companies.FirstCompany;
import com.nedelingg.companies.FourthCompany;
import com.nedelingg.companies.SecondCompany;
import com.nedelingg.companies.ThirdCompany;
import com.nedelingg.exceptions.NotEnoughShares;
import com.nedelingg.exceptions.UnsupportedCompanyID;
import com.nedelingg.stock.Share;

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

	public int putShares(List<Share> shares, CompanyID companyID) throws UnsupportedCompanyID {
		int moneys = shares.size() * this.getCompanyCurrentValue(companyID);
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
		
		/*int moneys = shares.size() * this.getCompanyCurrentValue(companyID);
		if (companyID.equals(CompanyID.FIRST)) {
			this.firstCompany.putShare(shares);
		} else if (companyID.equals(CompanyID.SECOND)) {
			this.secondCompany.putShare(shares);
		} else if (companyID.equals(CompanyID.THIRD)) {
			this.thirdCompany.putShare(shares);
		} else {
			this.fourthCompany.putShare(shares);
		}
		return moneys;*/
	}
	
	public List<Share> getShares(int intShares, CompanyID companyID) throws NotEnoughShares, UnsupportedCompanyID {
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
		
		/*if (companyID.equals(CompanyID.FIRST)) {
			return firstCompany.getShare(intShares);
		} else if (companyID.equals(CompanyID.SECOND)) {
			return secondCompany.getShare(intShares);
		} else if (companyID.equals(CompanyID.THIRD)) {
			return thirdCompany.getShare(intShares);
		} else {
			return fourthCompany.getShare(intShares);
		}*/
	}
	
	public boolean checkAvailableShares(int sharesAmount, CompanyID companyID) throws UnsupportedCompanyID{
		return sharesAmount <= this.getAvailableSharesCount(companyID);
		/*switch(companyID.value()){
			case 1: 
				return sharesAmount <= this.firstCompany.availableShares();
			case 2: 
				return sharesAmount <= this.secondCompany.availableShares();
			case 3: 
				return sharesAmount <= this.thirdCompany.availableShares();
			case 4: 
				return sharesAmount <= this.fourthCompany.availableShares();
			default: 
				throw new UnsupportedCompanyID("Not supported company ID");
		}	*/	
		/*int available;
		if (companyID.equals(CompanyID.FIRST)) {
			available = this.firstCompany.availableShares();
		} else if (companyID.equals(CompanyID.SECOND)) {
			available = this.secondCompany.availableShares();
		} else if (companyID.equals(CompanyID.THIRD)) {
			available = this.thirdCompany.availableShares();
		} else {
			available = this.fourthCompany.availableShares();
		}
		return sharesAmount <= available;*/
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
		/*int currentValue;
		if (companyID.equals(CompanyID.FIRST)) {
			currentValue = this.firstCompany.getCurrentValue();
		} else if (companyID.equals(CompanyID.SECOND)) {
			currentValue = this.secondCompany.getCurrentValue();
		} else if (companyID.equals(CompanyID.THIRD)) {
			currentValue = this.thirdCompany.getCurrentValue();
		} else {
			currentValue = this.fourthCompany.getCurrentValue();
		}
		return currentValue;*/
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
