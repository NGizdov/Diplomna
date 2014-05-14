package com.nedelingg.stock;

import java.util.Arrays;
import java.util.List;

import com.nedelingg.model.CompanyID;

public abstract class CompanyStock {
	protected List<Share> ones;
	protected List<Share> fives;
	
	private final static int ONE = 1;
	private final static int FIVE = 5;
	
	private int currentStock;
	public CompanyStock(CompanyID companyID) {
		this.ones = Arrays.asList(
				new Share(companyID, ONE)
				, new Share(companyID, ONE)
				, new Share(companyID, ONE)
				, new Share(companyID, ONE)
				, new Share(companyID, ONE)
				, new Share(companyID, ONE)
				, new Share(companyID, ONE)
				, new Share(companyID, ONE)
				, new Share(companyID, ONE)
				, new Share(companyID, ONE)
			);
		
		this.fives = Arrays.asList(
				new Share(companyID, FIVE)
				, new Share(companyID, FIVE)
				, new Share(companyID, FIVE)
				, new Share(companyID, FIVE)
				, new Share(companyID, FIVE)
				, new Share(companyID, FIVE)
			);
		
		this.currentStock = (this.fives.size() * FIVE) + (this.ones.size() * ONE);
	}
	
	public List<Share> getShare(int value) {
		return ones;		
	}
}
