package com.nedelingg.backend.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nedelingg.backend.cards.ByTwoCard;
import com.nedelingg.backend.cards.Card;
import com.nedelingg.backend.cards.HundredsCard;
import com.nedelingg.backend.cards.PercentageCard;
import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.decks.DeckTypes;
import com.nedelingg.backend.exceptions.NotEnoughMoney;
import com.nedelingg.backend.exceptions.NotEnoughShares;
import com.nedelingg.backend.exceptions.UnsupportedCompanyID;

public class Player {
	private String name;
	private int moneys;
	private List<HundredsCard> hudredCards;
	private List<PercentageCard> percentageCards;
	private List<ByTwoCard> byTwoCards;
	private List<Card> cards;
	private Map<Integer, Card> cardsIDs;
	private HashMap<CompanyID, Integer> shares;
	private List<CompanyID> sellForbidden;
	private List<CompanyID> buyForbidden;
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
		this.cards = new ArrayList<Card>(10);
		this.cardsIDs = new HashMap<>(10);
		this.sellForbidden = new ArrayList<CompanyID>(4);
		this.buyForbidden = new ArrayList<CompanyID>(4);
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
		
		cards.add(card);
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
	
	public Card getCardByID(int cardID) {
		return this.cardsIDs.get(cardID);
	}
	
	public HashMap<CompanyID, Integer> getShares() {
		return shares;
	}
	
	public void buyCompanyShares(int intShares, CompanyID companyID) throws NotEnoughMoney, NotEnoughShares, UnsupportedCompanyID {		
		int countedMoney = intShares * this.board.getCompanyCurrentValue(companyID);
		if (countedMoney > this.moneys)
			throw new NotEnoughMoney();
		
		int shares = this.board.getShares(intShares, companyID);
		int oldShares = 0;
		if (this.shares.get(companyID) != null) {
			oldShares = this.shares.get(companyID);
		}
		shares += oldShares;
		this.shares.put(companyID, shares);
		
		this.moneys -= countedMoney;
	} 
	
	public void sellCompanyShares(int intShares, CompanyID companyID) throws NotEnoughShares, UnsupportedCompanyID {
		if (this.shares.get(companyID) != null) {
			if (intShares > this.shares.get(companyID))
				throw new NotEnoughShares();		
		} else {
			throw new NotEnoughShares();
		}
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
	
	public Integer getSharesByCompID(CompanyID companyID){
		return this.shares.get(companyID);
	}

	public List<Card> getCards() {
		return cards;
	}
	
	public void setCardID(int cardID, Card card){
		this.cardsIDs.put(cardID, card);
	}
	
	public void addBuyForbidden(CompanyID companyID) {
		this.buyForbidden.add(companyID);
	}
	
	public void addSellForbidden(CompanyID companyID) {
		this.sellForbidden.add(companyID);
	}

	@SuppressWarnings("incomplete-switch")
	public void addLeftTreeBuyForbidden(CompanyID companyID) {
		switch (companyID) {
		case FIRST: 
			this.buyForbidden.add(CompanyID.SECOND);
			this.buyForbidden.add(CompanyID.THIRD);
			this.buyForbidden.add(CompanyID.FOURTH);
			break;
		case SECOND: 
			this.buyForbidden.add(CompanyID.FIRST);
			this.buyForbidden.add(CompanyID.THIRD);
			this.buyForbidden.add(CompanyID.FOURTH);
			break;
		case THIRD: 
			this.buyForbidden.add(CompanyID.SECOND);
			this.buyForbidden.add(CompanyID.FIRST);
			this.buyForbidden.add(CompanyID.FOURTH);
			break;
		case FOURTH: 
			this.buyForbidden.add(CompanyID.SECOND);
			this.buyForbidden.add(CompanyID.THIRD);
			this.buyForbidden.add(CompanyID.FIRST);
			break;
		}
	}
	
	@SuppressWarnings("incomplete-switch")
	public void addLeftTreeSellForbidden(CompanyID companyID) {
		switch (companyID) {
		case FIRST: 
			this.sellForbidden.add(CompanyID.SECOND);
			this.sellForbidden.add(CompanyID.THIRD);
			this.sellForbidden.add(CompanyID.FOURTH);
			break;
		case SECOND: 
			this.sellForbidden.add(CompanyID.FIRST);
			this.sellForbidden.add(CompanyID.THIRD);
			this.sellForbidden.add(CompanyID.FOURTH);
			break;
		case THIRD: 
			this.sellForbidden.add(CompanyID.SECOND);
			this.sellForbidden.add(CompanyID.FIRST);
			this.sellForbidden.add(CompanyID.FOURTH);
			break;
		case FOURTH: 
			this.sellForbidden.add(CompanyID.SECOND);
			this.sellForbidden.add(CompanyID.THIRD);
			this.sellForbidden.add(CompanyID.FIRST);
			break;
		}
	}
	
	public void resetBuyForbidden() {
		this.buyForbidden.clear();
	}

	public void resetSellForbidden() {
		this.sellForbidden.clear();
	}
	
	public List<CompanyID> getSellForbidden() {
		return sellForbidden;
	}

	public List<CompanyID> getBuyForbidden() {
		return buyForbidden;
	}
}
