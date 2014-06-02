package com.nedelingg.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.nedelingg.cards.ByTwoCard;
import com.nedelingg.cards.Card;
import com.nedelingg.cards.HundredsCard;
import com.nedelingg.cards.PercentageCard;
import com.nedelingg.companies.Company;
import com.nedelingg.companies.CompanyID;
import com.nedelingg.exceptions.NotEnoughMoney;
import com.nedelingg.exceptions.NotEnoughShares;
import com.nedelingg.stock.Share;

public class Player {
	private String name;
	private int moneys;
	private List<HundredsCard> hudredCards;
	private List<PercentageCard> percentageCards;
	private List<ByTwoCard> byTwoCards;
	private HashMap<CompanyID, List<Share>> shares;
	private Game game;
	
	public Player(Game game, String name) {
		this.name = name;
		this.game = game;
		this.moneys = 160;
		this.byTwoCards = new LinkedList<ByTwoCard>();
		this.percentageCards = new LinkedList<PercentageCard>();
		this.hudredCards = new LinkedList<HundredsCard>();
		this.shares = new HashMap<CompanyID, List<Share>>();
	}
	
	public void addCard(Card card) {
		if (card instanceof HundredsCard)
			this.hudredCards.add((HundredsCard) card);
		else if (card instanceof PercentageCard)
			this.percentageCards.add((PercentageCard) card);
		else if (card instanceof ByTwoCard)
			this.byTwoCards.add((ByTwoCard) card);
	}
	
	public void playCard(Card card) {
		// TODO play card
	}
	
	public void buyCompanyShares(int intShares, CompanyID companyID) throws NotEnoughMoney {
		
		int countedMoney = intShares * this.game.checkCompanyCurrentValue(companyID);
		if (countedMoney > this.moneys)
			throw new NotEnoughMoney();
		
//		List<Share> shares = company.getShare(intShares);
//		this.shares.put(new Integer(company.getId().value()), shares);
	} 
	
	public void sellCompanyShares(int intShares, CompanyID companyID) throws NotEnoughMoney, NotEnoughShares {
//		if (intShares > this.shares.get(company.getId()).size())
//			throw new NotEnoughShares();
//		
//		this.moneys += intShares * company.getCurrentValue();
//
//		this.game.putShares(intShares, company);
		// TODO Add shares to board company shares
	}

	public String getName() {
		return this.name;
	}
}
