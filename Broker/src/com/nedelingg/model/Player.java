package com.nedelingg.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.nedelingg.cards.ByTwoCard;
import com.nedelingg.cards.Card;
import com.nedelingg.cards.HundredsCard;
import com.nedelingg.cards.PercentageCard;
import com.nedelingg.companies.CompanyID;
import com.nedelingg.exceptions.NotEnoughMoney;
import com.nedelingg.exceptions.NotEnoughShares;
import com.nedelingg.exceptions.UnsupportedCompanyID;
import com.nedelingg.stock.Share;

public class Player {
	private String name;
	private int moneys;
	private List<HundredsCard> hudredCards;
	private List<PercentageCard> percentageCards;
	private List<ByTwoCard> byTwoCards;
	private HashMap<CompanyID, List<Share>> shares;
	private Board board;
	
	public Player(Board board, String name) {
		this.name = name;
		this.board = board;
		this.moneys = 300;
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
	
	public Card chooseCard() {
		int cardIndex = 0;
		Random rm =  new Random();
		int cardId = rm.nextInt(2);
		Card card = null;
		switch (cardId) {
			case 0: 
				cardIndex = rm.nextInt(this.hudredCards.size() - 1);
				card = this.hudredCards.remove(cardIndex);
				break;
			case 1:
				cardIndex = rm.nextInt(this.percentageCards.size() - 1);
				card = this.percentageCards.remove(cardIndex);
				break;
			case 2:
				cardIndex = rm.nextInt(this.byTwoCards.size() - 1);
				card = this.byTwoCards.remove(cardIndex);
				break;
		}
		return card;
	}
	
	public void buyCompanyShares(int intShares, CompanyID companyID) throws NotEnoughMoney, NotEnoughShares, UnsupportedCompanyID {		
		int countedMoney = intShares * this.board.getCompanyCurrentValue(companyID);
		if (countedMoney > this.moneys)
			throw new NotEnoughMoney();
		
		List<Share> shares = this.board.getShares(intShares, companyID);
		this.shares.put(companyID, shares);
	} 
	
	public void sellCompanyShares(int intShares, CompanyID companyID) throws NotEnoughShares, UnsupportedCompanyID {
		if (intShares > this.shares.get(companyID).size())
			throw new NotEnoughShares();
		
		List<Share> shares = new LinkedList<Share>();
		for (int i = 0; i < intShares; i++) {
			shares.add(this.shares.get(companyID).remove(0));			
		}
		
		this.moneys += this.board.putShares(shares, companyID);
	}
	
	public int checkCompanyShares(CompanyID companyID){
		return this.shares.get(companyID).size();
	}

	public String getName() {
		return this.name;
	}
}
