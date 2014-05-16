package com.nedelingg.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.nedelingg.cards.ByTwoCard;
import com.nedelingg.cards.HundredsCard;
import com.nedelingg.cards.PercentageCard;
import com.nedelingg.moneys.Money;
import com.nedelingg.stock.Share;

public class Player {
	private int moneys;
	private List<HundredsCard> hudredCards;
	private List<PercentageCard> percentageCards;
	private List<ByTwoCard> byTwoCards;
	private HashMap<Integer, LinkedList<Share>> shares;
}
