package com.nedelingg.backend.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import com.nedelingg.backend.actions.Lowerer;
import com.nedelingg.backend.actions.Raiser;
import com.nedelingg.backend.cards.Card;
import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.decks.ByTwoDeck;
import com.nedelingg.backend.decks.DeckTypes;
import com.nedelingg.backend.decks.HundredDeck;
import com.nedelingg.backend.decks.PercentageDeck;
import com.nedelingg.backend.exceptions.NotEnoughMoney;
import com.nedelingg.backend.exceptions.NotEnoughShares;
import com.nedelingg.backend.exceptions.UnsupportedCompanyID;
import com.nedelingg.backend.utils.Options;
import com.nedelingg.design.R;
import com.nedelingg.design.game.GameMainActivity;

public class Game {
	private List<Player> players;
	private Board board;
	private ByTwoDeck byTwoDeck;
	private HundredDeck hundredDeck;
	private PercentageDeck percentageDeck;
	private Scanner sc;
	
	public final static Random RANDOMISER = new Random();
	
	public Game(int numberPlayers) {
		this.players = new LinkedList<Player>();
		this.sc = new Scanner(System.in);
		this.board = new Board();
		boolean isHumanSet = false;
		for (int i = 0; i < numberPlayers; i++) {
			isHumanSet = Options.getPlayerType(i + 1);
			if(isHumanSet) break;
		}
		
		for (int i = 0; i < numberPlayers; i++) {
			String name = Options.getPlayerName(i + 1);
			boolean isHuman = Options.getPlayerType(i + 1);
			if(!isHumanSet && (i == 0)){
				isHuman = true;
				Options.setHumanPlayerIdInt(1);
			}
			this.players.add(i, new Player(this.board, name, isHuman));
		}
//		this.players.add(new Player(this.board, "Human", true));
		this.byTwoDeck = new ByTwoDeck();
		this.hundredDeck = new HundredDeck();
		this.percentageDeck =  new PercentageDeck();
		prepare();
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public Player getHumanPlayer(){
		Player humanPlayer = null;
		for (Player player : players) {
			if (player.isHuman()){
				humanPlayer = player;
				break;
			}
		}
		return humanPlayer;
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
	
	public void playPhaseCPU(Player player){
		Card card = null;
		List<DeckTypes> availableDecks = player.getDecksTypes();
		int randomIndex = RANDOMISER.nextInt(availableDecks.size());
		DeckTypes randomType = availableDecks.get(randomIndex);
		int size = player.getDeckSize(randomType);
		int randomCard = RANDOMISER.nextInt(size);
		card = player.chooseCard(randomType, randomCard);
		
		player.resetBuyForbidden();
		player.resetSellForbidden();
		
		chooseBuyOrSellCPU(player);
		
		playCPUCard(player, card);

		chooseBuyOrSellCPU(player);
	}
	
	private void finalPhase() {
		String winner = "";
		int maxMoney = 0;
		for (Player player : players) {
			player.sellAllShares();
			if (maxMoney < player.getMoneys()) {
				maxMoney = player.getMoneys();
				winner = player.getName();
			}
		}
		System.out.println("And the winner is: ###  " + winner + "  ###  --- with " + maxMoney + " dollars");
	}
	
	public Card getHumanCardByID(int cardID){
		for (Player player : this.players) {
			if (player.isHuman())
				return player.getCardByID(cardID);				
		}
		return null;
	}
	
	public void playCardWithChoose(Player player, Card card, CompanyID choosenCompany){
		Raiser raiser = card.getRaiser();
		Lowerer lower = card.getLowerer();
		CompanyID raiserCompanyID = raiser.getCompany();
		CompanyID lowerCompanyID = lower.getCompany();		
		
		if (raiserCompanyID.equals(CompanyID.BY_CHOICE)) {
			try {
				this.board.changeCompanyValue(choosenCompany, raiser);
				this.board.changeCompanyValue(lowerCompanyID, lower);
				player.addBuyForbidden(lowerCompanyID);
				player.addSellForbidden(choosenCompany);
			} catch (UnsupportedCompanyID e) {
				e.printStackTrace();
			}
		} else {
			try {
				this.board.changeCompanyValue(choosenCompany, lower);
				this.board.changeCompanyValue(raiserCompanyID, raiser);
				player.addBuyForbidden(choosenCompany);
				player.addSellForbidden(raiserCompanyID);
			} catch (UnsupportedCompanyID e) {
				e.printStackTrace();
			}
		}
	}
	
		
	public void playCard(Player player, int cardID){
		Card card = player.getCardByID(cardID);
		Raiser raiser = card.getRaiser();
		Lowerer lower = card.getLowerer();
		CompanyID raiserCompanyID = raiser.getCompany();
		CompanyID lowerCompanyID = lower.getCompany();
		
		if (raiserCompanyID.equals(CompanyID.ALL)) {
			this.board.changeAllCompaniesValue(raiser, lowerCompanyID);
			player.addLeftTreeSellForbidden(lowerCompanyID);
			try {
				this.board.changeCompanyValue(lowerCompanyID, lower);
				player.addBuyForbidden(lowerCompanyID);
			} catch (UnsupportedCompanyID e) {
				e.printStackTrace();
			}
		} else {
			this.board.changeAllCompaniesValue(lower, raiserCompanyID);
			player.addLeftTreeBuyForbidden(raiserCompanyID);
			try {
				this.board.changeCompanyValue(raiserCompanyID, raiser);
				player.addSellForbidden(raiserCompanyID);
			} catch (UnsupportedCompanyID e) {
				e.printStackTrace();
			}
		}
	}
	
	private void playCPUCard(Player player, Card card){
		Raiser raiser = card.getRaiser();
		Lowerer lower = card.getLowerer();
		CompanyID raiserCompanyID = raiser.getCompany();
		CompanyID lowerCompanyID = lower.getCompany();	
		
		if (raiserCompanyID.equals(CompanyID.BY_CHOICE) || lowerCompanyID.equals(CompanyID.BY_CHOICE)) {
			CompanyID choosenCompany = null;
			ArrayList<CompanyID> leftIDs = new ArrayList<CompanyID>();
			leftIDs.add(CompanyID.FIRST);
			leftIDs.add(CompanyID.SECOND);
			leftIDs.add(CompanyID.THIRD);
			leftIDs.add(CompanyID.FOURTH);
			if (raiserCompanyID.equals(CompanyID.BY_CHOICE)) {
				leftIDs.remove(lowerCompanyID);
				int choosenID = RANDOMISER.nextInt(3);
				choosenCompany = leftIDs.get(choosenID);
				try {
					this.board.changeCompanyValue(choosenCompany, raiser);
					this.board.changeCompanyValue(lowerCompanyID, lower);
					player.addBuyForbidden(lowerCompanyID);
					player.addSellForbidden(choosenCompany);
				} catch (UnsupportedCompanyID e) {
					e.printStackTrace();
				}
			} else {
				leftIDs.remove(raiserCompanyID);
				int choosenID = RANDOMISER.nextInt(3);
				choosenCompany = leftIDs.get(choosenID);
				try {
					this.board.changeCompanyValue(choosenCompany, lower);
					this.board.changeCompanyValue(raiserCompanyID, raiser);
					player.addBuyForbidden(choosenCompany);
					player.addSellForbidden(raiserCompanyID);
				} catch (UnsupportedCompanyID e) {
					e.printStackTrace();
				}
			}
		} else {
			if (raiserCompanyID.equals(CompanyID.ALL)) {
				this.board.changeAllCompaniesValue(raiser, lowerCompanyID);
				player.addLeftTreeSellForbidden(lowerCompanyID);
				try {
					this.board.changeCompanyValue(lowerCompanyID, lower);
					player.addBuyForbidden(lowerCompanyID);
				} catch (UnsupportedCompanyID e) {
					e.printStackTrace();
				}
			} else {
				this.board.changeAllCompaniesValue(lower, raiserCompanyID);
				player.addLeftTreeBuyForbidden(raiserCompanyID);
				try {
					this.board.changeCompanyValue(raiserCompanyID, raiser);
					player.addSellForbidden(raiserCompanyID);
				} catch (UnsupportedCompanyID e) {
					e.printStackTrace();
				}
			}
		}
	}
	/*private void playCPUCard(Player player, Card card){
		Raiser raiser = card.getRaiser();
		Lowerer lower = card.getLowerer();
		CompanyID raiserCompanyID = raiser.getCompany();
		CompanyID lowerCompanyID = lower.getCompany();

		CompanyID[] availableRaisers = null;
		switch (lowerCompanyID) {
		case FIRST:
			availableRaisers = new CompanyID[]{CompanyID.SECOND, CompanyID.THIRD, CompanyID.FOURTH};
			break;
		case SECOND:
			availableRaisers = new CompanyID[]{CompanyID.FIRST, CompanyID.THIRD, CompanyID.FOURTH};
			break;
		case THIRD:
			availableRaisers = new CompanyID[]{CompanyID.SECOND, CompanyID.FIRST, CompanyID.FOURTH};
			break;
		case FOURTH:
			availableRaisers = new CompanyID[]{CompanyID.SECOND, CompanyID.THIRD, CompanyID.FIRST};
			break;
		default:
			availableRaisers = new CompanyID[]{CompanyID.SECOND, CompanyID.THIRD, CompanyID.FIRST, CompanyID.FOURTH};
			break;
		}
		
		CompanyID[] availableLowers = null;
		switch (raiserCompanyID) {
		case FIRST:
			availableLowers = new CompanyID[]{CompanyID.SECOND, CompanyID.THIRD, CompanyID.FOURTH};
			break;
		case SECOND:
			availableLowers = new CompanyID[]{CompanyID.FIRST, CompanyID.THIRD, CompanyID.FOURTH};
			break;
		case THIRD:
			availableLowers = new CompanyID[]{CompanyID.SECOND, CompanyID.FIRST, CompanyID.FOURTH};
			break;
		case FOURTH:
			availableLowers = new CompanyID[]{CompanyID.SECOND, CompanyID.THIRD, CompanyID.FIRST};
			break;
		default:
			availableLowers = new CompanyID[]{CompanyID.SECOND, CompanyID.THIRD, CompanyID.FIRST, CompanyID.FOURTH};
			break;
		}
		
		if (!raiserCompanyID.equals(CompanyID.BY_CHOICE)) {
			if (raiserCompanyID.equals(CompanyID.ALL)) {
				this.board.changeAllCompaniesValue(raiser, lowerCompanyID);
			} else {
				try {
					this.board.changeCompanyValue(raiserCompanyID, raiser);
				} catch (UnsupportedCompanyID e) {
				}
			}
		} else {
			int companyID = RANDOMISER.nextInt(availableRaisers.length);
			
			CompanyID id = availableRaisers[companyID];
			
			/*int companyID = RANDOMISER.nextInt(4);
			
			CompanyID id = null;
			switch(companyID) {
				case 0 : id = CompanyID.FIRST;				
					break;
				case 1 : id = CompanyID.SECOND;	
					break;
				case 2 : id = CompanyID.THIRD;	
					break;
				case 3 : id = CompanyID.FOURTH;	
					break;
			}
			try {
				this.board.changeCompanyValue(id, raiser);
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
			}
		}
		if (!lowerCompanyID.equals(CompanyID.BY_CHOICE)) {
			if (lowerCompanyID.equals(CompanyID.ALL)) {
				this.board.changeAllCompaniesValue(lower, raiserCompanyID);
			} else {
				try {
					this.board.changeCompanyValue(lowerCompanyID, lower);
				} catch (UnsupportedCompanyID e) {
				}
			}
		} else {
			int companyID = RANDOMISER.nextInt(availableLowers.length);
			
			CompanyID id = availableLowers[companyID];
			
			/*int companyID = RANDOMISER.nextInt(4);
			CompanyID id = null;
			switch(companyID) {
				case 0 : id = CompanyID.FIRST;				
					break;
				case 1 : id = CompanyID.SECOND;	
					break;
				case 2 : id = CompanyID.THIRD;	
					break;
				case 3 : id = CompanyID.FOURTH;	
					break;
			}
			try {
				this.board.changeCompanyValue(id, lower);
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
			}
		}		
	}*/
		
	public Board getBoard() {
		return board;
	}

	private void chooseBuyOrSellCPU(Player player){
		int option = RANDOMISER.nextInt(3);
		switch (option) {
		case 1:
			buyShares(player);
			break;
		case 2:
			sellShares(player);
			break;
		default:
			return;
		}
	}
	
	private void buyShares(Player player){
		List<CompanyID> forbidenIDs = player.getBuyForbidden();
		ArrayList<CompanyID> leftIDs = new ArrayList<CompanyID>();
		leftIDs.add(CompanyID.FIRST);
		leftIDs.add(CompanyID.SECOND);
		leftIDs.add(CompanyID.THIRD);
		leftIDs.add(CompanyID.FOURTH);
		for (CompanyID companyID : forbidenIDs) {
			leftIDs.remove(companyID);
		}
		int i = 0;
		boolean toContinue = true;
		while (toContinue && (i < 5)) {
			i++;
			int intCompanyID = RANDOMISER.nextInt(leftIDs.size());
			
			CompanyID id = null;
			switch (intCompanyID) {
			case 0:
				id = CompanyID.FIRST;
				break;
			case 1:
				id = CompanyID.SECOND;
				break;
			case 2:
				id = CompanyID.THIRD;
				break;
			case 3:
				id = CompanyID.FOURTH;
				break;
			default:
				toContinue = false;
				return;
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
			
			int shareAmount = RANDOMISER.nextInt(availableShares);
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
			
			int intToContinue = RANDOMISER.nextInt(2);
			if (intToContinue == 0) 
				toContinue = false;
		}
	}
	
	private void sellShares(Player player){
		List<CompanyID> forbidenIDs = player.getSellForbidden();
		ArrayList<CompanyID> leftIDs = new ArrayList<CompanyID>();
		leftIDs.add(CompanyID.FIRST);
		leftIDs.add(CompanyID.SECOND);
		leftIDs.add(CompanyID.THIRD);
		leftIDs.add(CompanyID.FOURTH);
		for (CompanyID companyID : forbidenIDs) {
			leftIDs.remove(companyID);
		}
		int i = 0;
		boolean toContinue = true;
		
		while (toContinue && (i < 5)) {
			i++;
			int intCompanyID = RANDOMISER.nextInt(leftIDs.size());
			
			CompanyID id = null;
			switch (intCompanyID) {
			case 1:
				id = CompanyID.FIRST;
				break;
			case 2:
				id = CompanyID.SECOND;
				break;
			case 3:
				id = CompanyID.THIRD;
				break;
			case 4:
				id = CompanyID.FOURTH;
				break;
			default:
				return;
			}
			Set<CompanyID> ownShares = player.getOwnCompanies();
			if (ownShares == null) {
				return;
			}
			if (!ownShares.contains(id)) {
				continue;
			}
			
			int availableShares = player.checkCompanyShares(id);
			int shareAmount = RANDOMISER.nextInt(availableShares);
			try {
				player.sellCompanyShares(shareAmount, id);
			}  catch (NotEnoughShares e) {
				System.out.println("Not enough available shares");
				continue;
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
				continue;
			}
			
			int intToContinue = RANDOMISER.nextInt(2);
			if (intToContinue == 0) 
				toContinue = false;
		}
	}
}
