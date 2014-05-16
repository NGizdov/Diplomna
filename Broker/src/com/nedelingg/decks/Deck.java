package com.nedelingg.decks;

import java.util.Collections;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;

import com.nedelingg.cards.Card;

public abstract class Deck {
	protected List<Card> cards;

	public void shuffle(){
		Collections.shuffle(this.cards);
	}
	
	public Card popTopCard() {
		return cards.remove(cards.size());
	}
	
	public abstract boolean pushCard(Card card) throws UnsupportedDataTypeException;
}
