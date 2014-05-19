package com.nedelingg.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.nedelingg.cards.ByTwoCard;
import com.nedelingg.cards.Card;
import com.nedelingg.cards.HundredsCard;
import com.nedelingg.cards.PercentageCard;
import com.nedelingg.companies.Company;
import com.nedelingg.exceptions.NotEnoughMoney;
import com.nedelingg.exceptions.NotEnoughShares;
import com.nedelingg.stock.Share;

public class Player {
	private int moneys;
	private List<HundredsCard> hudredCards;
	private List<PercentageCard> percentageCards;
	private List<ByTwoCard> byTwoCards;
	private HashMap<Integer, List<Share>> shares;
	
	public Player() {
		this.moneys = 160;
		this.byTwoCards = new LinkedList<ByTwoCard>();
		this.percentageCards = new LinkedList<PercentageCard>();
		this.hudredCards = new LinkedList<HundredsCard>();
		this.shares = new HashMap<Integer, List<Share>>();
	}
	
	public void addCard(Card card) {
		if (card instanceof HundredsCard)
			this.hudredCards.add((HundredsCard) card);
		else if (card instanceof PercentageCard)
			this.percentageCards.add((PercentageCard) card);
		else if (card instanceof ByTwoCard)
			this.byTwoCards.add((ByTwoCard) card);
	}
	
	public void buyCompanyShares(int intShares, Company company) throws NotEnoughMoney, NotEnoughShares {
		if (intShares > company.getStock().getShares().size())
			throw new NotEnoughShares();
		
		int countedMoney = intShares * company.getCurrentValue();
		if (countedMoney > this.moneys)
			throw new NotEnoughMoney();
		
		List<Share> shares = company.getStock().getShare(intShares);
		this.shares.put(new Integer(company.getId().value()), shares);
	} 
}
