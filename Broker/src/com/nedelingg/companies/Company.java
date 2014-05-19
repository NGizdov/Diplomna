package com.nedelingg.companies;

import com.nedelingg.actions.Actioner;
import com.nedelingg.actions.Additioner;
import com.nedelingg.actions.Divider;
import com.nedelingg.actions.Multiplier;
import com.nedelingg.actions.Substractor;
import com.nedelingg.model.Board;
import com.nedelingg.stock.CompanyStock;

public abstract class Company {
	private CompanyID id;
	private int currentValue;
	protected CompanyStock stock;
	
	public Company(CompanyID id, CompanyStock stock) {
		this.id = id;
		this.currentValue = 100;
		this.stock = stock;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public CompanyID getId() {
		return id;
	}
	
	public CompanyStock getStock() {
		return stock;
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
}
