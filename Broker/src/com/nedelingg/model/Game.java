package com.nedelingg.model;

import java.util.List;
import java.util.Scanner;

import com.nedelingg.companies.Company;
import com.nedelingg.companies.CompanyID;
import com.nedelingg.decks.ByTwoDeck;
import com.nedelingg.decks.HundredDeck;
import com.nedelingg.decks.PercentageDeck;
import com.nedelingg.exceptions.NotEnoughMoney;
import com.nedelingg.exceptions.NotEnoughShares;

public class Game {
	private List<Player> players;
	private Board board;
	private ByTwoDeck byTwoDeck;
	private HundredDeck hundredDeck;
	private PercentageDeck percentageDeck;
	private int moneys;
	private Scanner sc;
	
	public Game(int players) {
		this.sc = new Scanner(System.in);
		for (int i = 0; i < players; i++) {
			this.players.add(new Player(this, "Player#" + (i + 1)));
		}
		this.board = new Board();
		this.byTwoDeck = new ByTwoDeck();
		this.hundredDeck = new HundredDeck();
		this.percentageDeck =  new PercentageDeck();
		this.moneys = 5000;
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
				chooseBuyOrSell(player);
			}
		}
	}
	
	public void putShares(int intShares, CompanyID companyID) {
		this.board.putShares(intShares, companyID);
	}
	
	public int checkCompanyCurrentValue(CompanyID companyID) {
		return this.board.getCompanyCurrentValue(companyID);
	}
	
	private void chooseBuyOrSell(Player player){
		boolean skip = false;
		while(!skip) {
			System.out.println(player.getName() + " choose option: ");
			System.out.println("\t0. Skip");
			System.out.println("\t1. Buy");
			System.out.println("\t2. Sell");
			int answer = this.sc.nextInt();
			if ((answer < 0) && (answer > 2)) {
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
			if ((companyID < 0) && (companyID > 4)) {
				continue;
			}
			
			System.out.println(player.getName() + " choose the amount of the shares to buy (1 - 40): ");
			int shareAmount = this.sc.nextInt();
			if ((shareAmount < 1) && (shareAmount > 40)) {
				continue;
			}
			
//			boolean enought = this.board.checkAvailableShares(shareAmount, companyID);
			switch(companyID) {
				case 1 : 
					boolean enought = this.board.checkAvailableShares(shareAmount, CompanyID.FIRST);
					if (enought) {
						try {
							player.buyCompanyShares(shareAmount, CompanyID.FIRST);
						} catch (NotEnoughMoney e) {
							System.out.println("Not enough moneys");
							continue;
						}
					} else {
						System.out.println("Not enough available shares");
						continue;
					}
					break;
				case 2 : sellShares(player);
					break;
				case 3 : sellShares(player);
					break;
				case 4 : sellShares(player);
					break;
				default : skip = true;
					break;
			}
		}
	}
	
	private void sellShares(Player player){
		
	}
}
