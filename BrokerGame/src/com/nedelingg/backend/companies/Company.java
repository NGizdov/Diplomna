package com.nedelingg.backend.companies;

import com.nedelingg.backend.actions.Actioner;
import com.nedelingg.backend.actions.Additioner;
import com.nedelingg.backend.actions.Divider;
import com.nedelingg.backend.actions.Multiplier;
import com.nedelingg.backend.actions.Substractor;
import com.nedelingg.backend.exceptions.NotEnoughShares;
import com.nedelingg.backend.model.Board;
import com.nedelingg.backend.utils.Options;

public abstract class Company {
	private CompanyID id;
	private int currentValue;
//	protected CompanyStock stock;
	private int shares;
	private String name;
//	public Company(CompanyID id, CompanyStock stock) {
//		this.id = id;
//		this.currentValue = 100;
//		this.stock = stock;
//	}
	public Company(CompanyID id) {
//		switch (id) {
//			case FIRST: 
//				if (OptionActivity.getCompanyOneNameString().length() > 0) 
//					this.name = OptionActivity.getCompanyOneNameString();
//				else 
//					this.name = OptionActivity.COMPANY_ONE_DEFAULT_NAME_STRING;
//				break;
//			case SECOND: 
//				if (OptionActivity.getCompanyTwoNameString().length() > 0) 
//					this.name = OptionActivity.getCompanyTwoNameString();
//				else 
//					this.name = OptionActivity.COMPANY_TWO_DEFAULT_NAME_STRING;
//				break;
//			case THIRD: 
//				if (OptionActivity.getCompanyTreeNameString().length() > 0) 
//					this.name = OptionActivity.getCompanyTreeNameString();
//				else 
//					this.name = OptionActivity.COMPANY_TREE_DEFAULT_NAME_STRING;
//				break;
//			case FOURTH: 
//				if (OptionActivity.getCompanyFourNameString().length() > 0) 
//					this.name = OptionActivity.getCompanyFourNameString();
//				else 
//					this.name = OptionActivity.COMPANY_FOUR_DEFAULT_NAME_STRING;
//				break;
//		default:
//			this.name = OptionActivity.UNKNOWN_COMPANY_NAME_STRING;
//			break;
//		}
		this.name = Options.getCompanyName(id);
		this.id = id;
		this.currentValue = 100;
		this.shares = 40;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public CompanyID getId() {
		return id;
	}
	
	public int availableShares() {
//		return stock.getShares().size();
		return shares;
	}

	public int changeCurrentValue(Actioner action) {
		if (action instanceof Additioner) {
			this.currentValue = this.currentValue + action.getValue().getValue();
		} else if (action instanceof Substractor) {
			this.currentValue = this.currentValue - action.getValue().getValue();
		} else if (action instanceof Multiplier) {
			this.currentValue = this.currentValue * action.getValue().getValue();
		} else if (action instanceof Divider) {
			this.currentValue = this.currentValue / action.getValue().getValue();
			if ((this.currentValue % 10) != 0) {
				this.currentValue = this.currentValue - (this.currentValue % 10);
			}
		}
		
		int reminder = 0;
		if (this.currentValue > Board.MAX_SHARE_PRIZE) {
			reminder = this.currentValue - Board.MAX_SHARE_PRIZE;
			this.currentValue = Board.MAX_SHARE_PRIZE;
		} else if (this.currentValue < Board.MIN_SHARE_PRIZE) {
			reminder = this.currentValue - Board.MIN_SHARE_PRIZE;
			this.currentValue = Board.MIN_SHARE_PRIZE;
		}
		
		return reminder;
	}

	/*public List<Share> getShare(int value) throws NotEnoughShares {
		return this.stock.getShares(value);
	}
	
	public boolean putShare(List<Share> shares) {
		return this.stock.putShares(shares);
	}*/
	
	public int getShare(int value) throws NotEnoughShares {
		if (value > this.shares) throw new NotEnoughShares();
		this.shares -= value;
		return value;
	}
	
	public void putShare(int shares) {
		this.shares += shares;
	}

	public String getName() {
		return name;
	}
}
