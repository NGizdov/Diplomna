package com.nedelingg.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.nedelingg.actions.Lowerer;
import com.nedelingg.actions.Raiser;
import com.nedelingg.cards.Card;
import com.nedelingg.companies.CompanyID;
import com.nedelingg.decks.ByTwoDeck;
import com.nedelingg.decks.HundredDeck;
import com.nedelingg.decks.PercentageDeck;
import com.nedelingg.exceptions.NotEnoughMoney;
import com.nedelingg.exceptions.NotEnoughShares;
import com.nedelingg.exceptions.UnsupportedCompanyID;

public class Game {
	private List<Player> players;
	private Board board;
	private ByTwoDeck byTwoDeck;
	private HundredDeck hundredDeck;
	private PercentageDeck percentageDeck;
	private Scanner sc;
	
	public Game(int players) {
		this.players = new LinkedList<Player>();
		this.sc = new Scanner(System.in);
		this.board = new Board();
		for (int i = 0; i < players; i++) {
			String name = "Player#" + (i + 1);
			this.players.add(new Player(this.board, name));
		}
		this.byTwoDeck = new ByTwoDeck();
		this.hundredDeck = new HundredDeck();
		this.percentageDeck =  new PercentageDeck();
	}
	
	public void prepare(){
		this.byTwoDeck.shuffle();
		this.hundredDeck.shuffle();
		this.percentageDeck.shuffle();
		
		for (int i = 0; i < 3; i++){
			for (Player player : players) {
				player.addCard(this.byTwoDeck.popTopCard());
			}			
		}
		
		for (int i = 0; i < 2; i++){
			for (Player player : players) {
				player.addCard(this.hundredDeck.popTopCard());
			}			
		}
		
		for (int i = 0; i < 5; i++){
			for (Player player : players) {
				player.addCard(this.percentageDeck.popTopCard());
			}			
		}
	}
	
	public void playPhase(){		
		boolean isFinished = false;
		while(!isFinished) {
			for (Player player : players) {
				phaseOne(player);
				Card card = player.chooseCard();
				phaseTwo(card);
			}
		}
	}
	
	private void phaseTwo(Card card) {
//		Card card = player.chooseCard();
		Raiser raiser = card.getRaiser();
		Lowerer lower = card.getLowerer();
		CompanyID raiserCompanyID = raiser.getCompany();
		CompanyID lowerCompanyID = lower.getCompany();
		if (raiserCompanyID.equals(CompanyID.ALL)) {
			this.board.changeAllCompaniesValue(raiser);
		} else if (raiserCompanyID.equals(CompanyID.BY_CHOICE)) {
			int companyID = -100;
			while(true){
				System.out.println("Please choose company to raise the value: ");
				System.out.println("\t1. First");
				System.out.println("\t2. Second");
				System.out.println("\t3. Third");
				System.out.println("\t4. Fourth");
				companyID = this.sc.nextInt();
				if ((companyID < 0) || (companyID > 4)) {
					continue;
				}
				break;
			}
			CompanyID id = null;
			switch(companyID) {
				case 1 : id = CompanyID.FIRST;				
					break;
				case 2 : id = CompanyID.SECOND;	
					break;
				case 3 : id = CompanyID.THIRD;	
					break;
				case 4 : id = CompanyID.FOURTH;	
					break;
			}
			try {
				this.board.changeCompanyValue(id, raiser);
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
			}
		} else {
			try {
				this.board.changeCompanyValue(raiserCompanyID, raiser);
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
			}
		}
		if (lowerCompanyID.equals(CompanyID.ALL)) {
			this.board.changeAllCompaniesValue(lower);
		} else if (lowerCompanyID.equals(CompanyID.BY_CHOICE)) {
			int companyID = -100;
			while(true){
				System.out.println("Please choose company to lower the value: ");
				System.out.println("\t1. First");
				System.out.println("\t2. Second");
				System.out.println("\t3. Third");
				System.out.println("\t4. Fourth");
				companyID = this.sc.nextInt();
				if ((companyID < 0) || (companyID > 4)) {
					continue;
				}
				break;
			}
			CompanyID id = null;
			switch(companyID) {
				case 1 : id = CompanyID.FIRST;				
					break;
				case 2 : id = CompanyID.SECOND;	
					break;
				case 3 : id = CompanyID.THIRD;	
					break;
				case 4 : id = CompanyID.FOURTH;	
					break;
			}
			try {
				this.board.changeCompanyValue(id, lower);
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
			}
		} else {
			try {
				this.board.changeCompanyValue(lowerCompanyID, lower);
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
			}
		}
	}

	private void phaseOne(Player player) {
		chooseBuyOrSell(player);		
	}
	
	private void chooseBuyOrSell(Player player){
		boolean skip = false;
		while(!skip) {
			System.out.println(player.getName() + " choose option: ");
			System.out.println("\t0. Skip");
			System.out.println("\t1. Buy");
			System.out.println("\t2. Sell");
			int answer = this.sc.nextInt();
			if ((answer < 0) || (answer > 2)) {
				continue;
			}
			
			switch(answer) {
				case 1 : buyShares(player);
					break;
				case 2 : sellShares(player);
					break;
				default : skip = true;
					break;
			}
		}
	}
	
	private void buyShares(Player player){
		boolean skip = false;
		while(!skip) {
			System.out.println(player.getName() + " choose company to buy shares: ");
			System.out.println("\t1. First");
			System.out.println("\t2. Second");
			System.out.println("\t3. Third");
			System.out.println("\t4. Fourth");
			System.out.println("\t0. Skip");
			int companyID = this.sc.nextInt();
			if ((companyID < 0) || (companyID > 4)) {
				continue;
			}			
			
//			boolean enought = this.board.checkAvailableShares(shareAmount, companyID);
			CompanyID id = null;
			switch(companyID) {
				case 1 : id = CompanyID.FIRST;				
					break;
				case 2 : id = CompanyID.SECOND;	
					break;
				case 3 : id = CompanyID.THIRD;	
					break;
				case 4 : id = CompanyID.FOURTH;	
					break;
				default : skip = true;
					continue;
			}
			
			int availableShares = 0;
			try {
				availableShares = board.getAvailableSharesCount(id);
			} catch (UnsupportedCompanyID e2) {
				System.out.println("Not supported company ID");
				continue;
			}
			
			if (availableShares < 1) {
				System.out.println("No more shares for that company");
				continue;
			}
			System.out.println(player.getName() + " choose the amount of the shares to buy (1 - " + availableShares + "): ");
			int shareAmount = this.sc.nextInt();
			if ((shareAmount < 1) || (shareAmount > availableShares)) {
				System.err.println("Enter the right amount of shares");
				continue;
			}
			try {
				player.buyCompanyShares(shareAmount, id);
			} catch (NotEnoughMoney e) {
				System.out.println("Not enough moneys");
				continue;
			} catch (NotEnoughShares e) {
				System.out.println("Not enough available shares");
				continue;
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
				continue;
			}
		}
	}
	
	private void sellShares(Player player){
		boolean skip = false;
		while(!skip) {
			System.out.println(player.getName() + " choose company to sell shares: ");
			System.out.println("\t1. First");
			System.out.println("\t2. Second");
			System.out.println("\t3. Third");
			System.out.println("\t4. Fourth");
			System.out.println("\t0. Skip");
			int companyID = this.sc.nextInt();
			if ((companyID < 0) || (companyID > 4)) {
				continue;
			}
			
			CompanyID id = null;
			switch(companyID) {
				case 1 : id = CompanyID.FIRST;				
					break;
				case 2 : id = CompanyID.SECOND;	
					break;
				case 3 : id = CompanyID.THIRD;	
					break;
				case 4 : id = CompanyID.FOURTH;	
					break;
				default : skip = true;
					continue;
			}
			
			int availableShares = player.checkCompanyShares(id);
			System.out.println(player.getName() + " choose the amount of the shares to sell (1 - " + availableShares + "): ");
			int shareAmount = this.sc.nextInt();
			if ((shareAmount < 1) || (shareAmount > availableShares)) {
				System.err.println("Enter the right amount of shares");
				continue;
			}
			try {
				player.sellCompanyShares(shareAmount, id);
			} catch (NotEnoughShares e) {
				System.out.println("Not enough available shares");
				continue;
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
				continue;
			}
		}
	}
}
