package com.nedelingg.model;

import java.util.List;

import com.nedelingg.decks.ByTwoDeck;
import com.nedelingg.decks.HundredDeck;
import com.nedelingg.decks.PercentageDeck;
import com.nedelingg.moneys.Money;
import com.nedelingg.stock.FirstCompanyStock;
import com.nedelingg.stock.FourthCompanyStock;
import com.nedelingg.stock.SecondCompanyStock;
import com.nedelingg.stock.ThirdCompanyStock;

public class Game {
	private List<Player> players;
	private Board board;
	private ByTwoDeck byTwoDeck;
	private HundredDeck hundredDeck;
	private PercentageDeck percentageDeck;
	private FirstCompanyStock firstCompanyStock;
	private SecondCompanyStock secondCompanyStock;
	private ThirdCompanyStock thirdCompanyStock;
	private FourthCompanyStock fourthCompanyStock;
	private int moneys;
}
