package com.nedelingg.stock;

import java.util.LinkedList;
import java.util.List;

import com.nedelingg.companies.CompanyID;
import com.nedelingg.exceptions.NotEnoughShares;

public abstract class CompanyStock {
	protected List<Share> shares;

	public List<Share> getShares() {
		return shares;
	}

	private final static int ONE = 1;

	public CompanyStock(CompanyID companyID) {
		this.shares = new LinkedList<Share>();
		for (int i = 0; i < 40; i++) {
			this.shares.add(new Share(companyID, ONE));
		}
	}

	public List<Share> getShares(int value) throws NotEnoughShares {
		if (value > this.shares.size()){
			throw new NotEnoughShares("Not enough shares");
		}
		List<Share> returned = new LinkedList<Share>();
		int length = this.shares.size();
		for (int i = length - 1; i >= 0; i--) {
			returned.add(this.shares.remove(i));
		}
		return shares;
	}
	
	public boolean putShares(int value) {
//		this.shares.
		return true;
	}
}
