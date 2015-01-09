package com.nedelingg.backend.model;

import java.util.LinkedList;
import java.util.List;
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
		
	//	chooseBuyOrSellCPU(player);
		
		playCPUCard(player, card);

		//chooseBuyOrSellCPU(player);
	}
	
	public void playPhase(){
		for (int i = 0; i < 10; i++) {
			for (Player player : players) {
				Card card = phaseOne(player);
				phaseTwo(player);
				phaseTree(player, card);
				phaseFour(player);
			}
		} 
		
		finalPhase();
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

	private Card phaseOne(Player player) {
		Card card = null;
		List<DeckTypes> availableDecks = player.getDecksTypes();
		if (player.isHuman()) {
			while (card == null) {
				int typeCard = 0;
				while (true) {
					System.out.println("Please choose card type to play: ");
					if (availableDecks.contains(DeckTypes.Hundreds)) System.out.println("\t1. Hundreds");
					if (availableDecks.contains(DeckTypes.ByTwo)) System.out.println("\t2. By Two");
					if (availableDecks.contains(DeckTypes.Percentage)) System.out.println("\t3. 60/40");
					typeCard = this.sc.nextInt();
					if ((typeCard < 1) || (typeCard > 3)) {
						continue;
					}
					break;
				}
				DeckTypes choosenDeck = null;
				switch (typeCard) {
					case 1: choosenDeck = DeckTypes.Hundreds;
						break;
					case 2: choosenDeck = DeckTypes.ByTwo;
						break;
					case 3: choosenDeck = DeckTypes.Percentage;
						break;
				}
				int size = player.getDeckSize(choosenDeck);
				int indexCard = 0;
				while (true) {
					System.out.println("Please choose card index to play (1 - " + size + "): ");
					for (int i = 0; i < size; i++) {
						System.out.println("\t" +(i + 1) + ". " + (i + 1));						
					}
					indexCard = this.sc.nextInt();
					if ((indexCard < 1) || (indexCard > size)) {
						continue;
					}
					break;
				}
				card = player.getCardByID(indexCard);
			}
		} else {
			int randomIndex = RANDOMISER.nextInt(availableDecks.size());
			DeckTypes randomType = availableDecks.get(randomIndex);
			int size = player.getDeckSize(randomType);
			int randomCard = RANDOMISER.nextInt(size);
			card = player.chooseCard(randomType, randomCard);
		}
		return card;
	}
	
	private void phaseTwo(Player player) {
		chooseBuyOrSell(player);				
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
			} catch (UnsupportedCompanyID e) {
				e.printStackTrace();
			}
		} else {
			try {
				this.board.changeCompanyValue(choosenCompany, lower);
				this.board.changeCompanyValue(raiserCompanyID, raiser);
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
				try {
					this.board.changeCompanyValue(lowerCompanyID, lower);
				} catch (UnsupportedCompanyID e) {
					e.printStackTrace();
				}
		} else {
			this.board.changeAllCompaniesValue(lower, raiserCompanyID);
			try {
				this.board.changeCompanyValue(raiserCompanyID, raiser);
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
			}*/
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
			}*/
			try {
				this.board.changeCompanyValue(id, lower);
			} catch (UnsupportedCompanyID e) {
				System.out.println("Not supported company ID");
			}
		}		
	}
	
	private void phaseTree(Player player, Card card){
		Raiser raiser = card.getRaiser();
		Lowerer lower = card.getLowerer();
		CompanyID raiserCompanyID = raiser.getCompany();
		CompanyID lowerCompanyID = lower.getCompany();
		
		
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
			if (player.isHuman()) {
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
				int companyID = RANDOMISER.nextInt(4);
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
			if (player.isHuman()) {
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
				int companyID = RANDOMISER.nextInt(4);
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
		}
	}
	
	public Board getBoard() {
		return board;
	}

	private void phaseFour(Player player) {
		chooseBuyOrSell(player);				
	}
	
	private void chooseBuyOrSell(Player player){
		if (player.isHuman()) {
			while (true) {
				System.out.println(player.getName() + " choose option: ");
				System.out.println("\t0. Skip");
				System.out.println("\t1. Buy");
				System.out.println("\t2. Sell");
				int answer = this.sc.nextInt();
				if ((answer < 0) || (answer > 2)) {
					continue;
				}

				switch (answer) {
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
		} else {
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
		if (player.isHuman()) {
			while (true) {
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
				switch (companyID) {
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
		} else {
			while (true) {
				int intCompanyID = RANDOMISER.nextInt(5);
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
			}
		}
	}
	
	private void sellShares(Player player){
		if (player.isHuman()) {
			while (true) {
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
				switch (companyID) {
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
					System.out.println("You don't have shares of that company");
					continue;
				}

				int availableShares = player.checkCompanyShares(id);
				System.out.println(player.getName() + " choose the amount of the shares to sell (1 - " + availableShares + "): ");
				int shareAmount = this.sc.nextInt();
				if ((shareAmount < 0) || (shareAmount > availableShares)) {
					System.err.println("Enter the right amount of shares");
					continue;
				}
				if (shareAmount == 0) {
					return;
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
		} else {
			while (true) {
//				System.out.println("");
				int intCompanyID = RANDOMISER.nextInt(5);
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
			}
		}
	}
}
