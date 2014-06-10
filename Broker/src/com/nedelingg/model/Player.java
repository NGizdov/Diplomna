package com.nedelingg.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.nedelingg.cards.ByTwoCard;
import com.nedelingg.cards.Card;
import com.nedelingg.cards.HundredsCard;
import com.nedelingg.cards.PercentageCard;
import com.nedelingg.companies.CompanyID;
import com.nedelingg.decks.DeckTypes;
import com.nedelingg.exceptions.NotEnoughMoney;
import com.nedelingg.exceptions.NotEnoughShares;
import com.nedelingg.exceptions.UnsupportedCompanyID;

public class Player {
	private String name;
	private int moneys;
	private List<HundredsCard> hudredCards;
	private List<PercentageCard> percentageCards;
	private List<ByTwoCard> byTwoCards;
	private HashMap<CompanyID, Integer> shares;
	private Board board;
	private boolean isHuman;
	
	public Player(Board board, String name, boolean isHuman) {
		this.isHuman = isHuman;
		this.name = name;
		this.board = board;
		this.moneys = 300;
		this.byTwoCards = new ArrayList<ByTwoCard>();
		this.percentageCards = new ArrayList<PercentageCard>();
		this.hudredCards = new ArrayList<HundredsCard>();
		this.shares = new HashMap<CompanyID, Integer>();
	}
	
	public Player(Board board, String name) {
		this(board, name, false);
	}
	
	public void addCard(Card card) {
		if (card instanceof HundredsCard)
			this.hudredCards.add((HundredsCard) card);
		else if (card instanceof PercentageCard)
			this.percentageCards.add((PercentageCard) card);
		else if (card instanceof ByTwoCard)
			this.byTwoCards.add((ByTwoCard) card);
	}
	
	public Card chooseCard(DeckTypes type, int index) {
		Card card = null;
		try {
			switch (type) {
				case Hundreds: 
					card = this.hudredCards.remove(index);
					break;
				case Percentage:
					card = this.percentageCards.remove(index);
					break;
				case ByTwo:
					card = this.byTwoCards.remove(index);
					break;
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Sorry but there is no such card, choose other");
		}
		return card;
	}
	
	public void buyCompanyShares(int intShares, CompanyID companyID) throws NotEnoughMoney, NotEnoughShares, UnsupportedCompanyID {		
		int countedMoney = intShares * this.board.getCompanyCurrentValue(companyID);
		if (countedMoney > this.moneys)
			throw new NotEnoughMoney();
		
		int shares = this.board.getShares(intShares, companyID);
		this.shares.put(companyID, shares);
		
		this.moneys -= countedMoney;
	} 
	
	public void sellCompanyShares(int intShares, CompanyID companyID) throws NotEnoughShares, UnsupportedCompanyID {
		if (intShares > this.shares.get(companyID))
			throw new NotEnoughShares();		
	/*	List<Share> shares = new LinkedList<Share>();
		for (int i = 0; i < intShares; i++) {
			shares.add(this.shares.get(companyID).remove(0));			
		}*/
		this.shares.put(companyID, this.shares.get(companyID) - intShares);
//		this.shares.get(companyID);		
		this.moneys += this.board.putShares(intShares, companyID);
	}
	
	public void sellAllShares() {
		try {
			for (CompanyID id : this.shares.keySet()) {
				this.moneys += this.board.putShares(shares.get(id), id);
			}
		} catch (UnsupportedCompanyID e) {
		}
	}
	
	public int checkCompanyShares(CompanyID companyID){
		return this.shares.get(companyID);
	}

	public String getName() {
		return this.name;
	}
	
	public boolean isHuman() {
		return this.isHuman;
	}
	
	public List<DeckTypes> getDecksTypes(){
		List<DeckTypes> decks = new ArrayList<DeckTypes>();
		if (this.hudredCards.size() > 0) decks.add(DeckTypes.Hundreds);
		if (this.byTwoCards.size() > 0) decks.add(DeckTypes.ByTwo);
		if (this.percentageCards.size() > 0) decks.add(DeckTypes.Percentage);
		return decks;
	}
	
	public int getDeckSize(DeckTypes type){
		int size = 0;
		if (type.equals(DeckTypes.Hundreds)) size = this.hudredCards.size();
		else if (type.equals(DeckTypes.ByTwo)) size = this.byTwoCards.size();
		else size = this.percentageCards.size();
		return size;
	}
	
	public int getMoneys() {
		return this.moneys;
	}
	
	public Set<CompanyID> getOwnCompanies(){
		if (this.shares.size() == 0) {
			return null;
		}
		return this.shares.keySet();
	}
}
